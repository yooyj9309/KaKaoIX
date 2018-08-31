package com.example.demo.service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.domain.ProductVO;
import com.example.demo.exception.InvalidInputException;
import com.example.demo.exception.NoAuthException;
import com.example.demo.exception.SQLErrorException;
import com.example.demo.exception.ServerErrorException;
import com.example.demo.mapper.MainMapper;
import com.example.demo.utils.ImgUtil;

@Service("com.example.demo.service.MainService")
public class MainService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MainService.class);

	@Autowired
	private MainMapper mainMapper;

	public List<ProductVO> getProductList() {
		List<ProductVO> productList = null;
		try {
			productList = mainMapper.selectProductList();
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw new ServerErrorException("리스트를 불러오는 데 실패하였습니다.");
		}
		return productList;
	}

	public ProductVO getProductById(int productId) {
		ProductVO product = null;
		try {
			product = mainMapper.selectProductById(productId);
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw new ServerErrorException("물품 정보를 불러오는 데 실패하였습니다.");
		}
		return product;
	}

	public List<ProductVO> getMyProductList(String userId) {
		List<ProductVO> productList = null;
		try {
			productList = mainMapper.selectMyProductList(userId);
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw new ServerErrorException("구매 리스트를 불러오는 데 실패하였습니다.");
		}
		return productList;
	}
	
	public void recordTransaction(Map<String,Object> map) {
		try {
			if(mainMapper.selectAmount(map)==null ) {
				mainMapper.insertTransaction(map);
			}
			else {
				LOGGER.info("Update amount");
				mainMapper.updateAmount(map);
			}		
		}catch(DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw new ServerErrorException("거래를 기록하는 데 실패하였습니다.");
		}
	}

	public void registerProduct(ProductVO productInformation, HttpSession session) {
		String productDescirbe = productInformation.getProductDescribe();
		String productName = productInformation.getProductName();
		String savePath = "images";
		String fileName = productInformation.getImgFile().getOriginalFilename();

		int price = productInformation.getProductPrice();

		if (StringUtils.isEmpty(productName)) {
			throw new InvalidInputException("상품명을 적어주세요.");
		}
		if (StringUtils.isEmpty(productDescirbe)) {
			throw new InvalidInputException("세부사항을 입력해주세요.");
		}
		if (price < 0) {
			throw new InvalidInputException("시작 가격이 너무 낮습니다.");
		}
		if (!isPhotoFile(fileName)) {
			throw new InvalidInputException("사진만 입력해주세요.");
		}
		if (productDescirbe.length() > 3000) {
			throw new InvalidInputException("너무 긴 세부사항입니다.");
		}
		if (productName.length() > 1000) {
			throw new InvalidInputException("너무 긴 이름입니다.");
		}

		String imgPath = ImgUtil.imgUpload(savePath, session, productInformation.getImgFile(),
				productInformation.getImgPath());
		productInformation.setImgPath(imgPath);

		try {
			mainMapper.insertProductInformation(productInformation);
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			deletePhoto(imgPath);
			throw new SQLErrorException("서버 에러 입니다. insert");
		}
	}

	private void deletePhoto(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			if (file.delete()) {
				LOGGER.info(filePath + "삭제 성공");
			} else {
				LOGGER.info(filePath + "삭제 성공");
			}
		} else {
			LOGGER.info(filePath + "가 존재하지 않습니다.");
		}
	}

	private boolean isPhotoFile(String str) {
		String allowPattern = ".+\\.(jpg|png|JPG|PNG)$";
		boolean result = false;

		Pattern p = Pattern.compile(allowPattern);
		Matcher m = p.matcher(str);
		result = m.matches();

		if (StringUtils.isEmpty(str))
			result = true;
		return result;
	}
}
