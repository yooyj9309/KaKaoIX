package com.example.demo.utils;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exception.ServerErrorException;

public class ImgUtil {
	final static String basicImgPath = "../images/ryan.png";

	public static String getRealPath(String savePath, HttpSession session) {
		String realPath = session.getServletContext().getRealPath(savePath);
		return realPath;
	}

	public static void makeFolder(String path) {
		File file = new File(path);
		file.mkdirs();
	}

	public static String renameTo(String path, String name) {

		makeFolder(path);

		String fileName = name;
		File file = new File(path, fileName);
		while (file.exists()) {
			UUID uuid = UUID.randomUUID();
			fileName = uuid.toString() + "_" + fileName;
			file = new File(path, fileName);
		}
		return fileName;
	}

	public static String imgUpload(String savePath, HttpSession session, MultipartFile imgfile, String fileimgPath) {
		String imgPath = "";
		try {
			String realPath = ImgUtil.getRealPath(savePath, session);
			String auctionImgFileName = imgfile.getOriginalFilename();
			String saveName = "";
			
			if (!StringUtils.isEmpty(auctionImgFileName)) {
				saveName = ImgUtil.renameTo(realPath, auctionImgFileName);
				try {
					File file = new File(realPath, saveName);
					imgfile.transferTo(file);
					imgPath = "../" + savePath + "/" + saveName;
				} catch (Exception e) {
					throw new ServerErrorException("이미지 업로드에 실패했습니다.");
				}
			} else {
				if (!StringUtils.isEmpty(fileimgPath)) {
					imgPath = fileimgPath;
				} else {
					imgPath = basicImgPath;
				}
			}
		} catch (Exception e) {
			imgPath = basicImgPath;
		}
		return imgPath;
	}

}