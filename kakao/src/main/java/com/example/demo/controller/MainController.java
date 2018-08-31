package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.ProductVO;
import com.example.demo.service.MainService;



@RestController
public class MainController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private MainService mainService;
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView getMainView() {
		ModelAndView mav = new ModelAndView();
		List<ProductVO> productList = mainService.getProductList();
		mav.addObject("productList", productList);
		mav.setViewName("mainView");
		return mav;
	}

	@RequestMapping(value = "product/{productId}", method = RequestMethod.GET)
	public ModelAndView getDetailView(@PathVariable("productId") int productId, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		ProductVO product = mainService.getProductById(productId);
		mav.addObject("product", product);
		mav.setViewName("productView");
		return mav;
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public ModelAndView getFormView() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("form");
		return mav;
	}
	
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public ModelAndView getMyPageView(HttpSession session, ModelAndView mav) {
		String userId = (String) session.getAttribute("userId");
		
		List<ProductVO> productList = mainService.getMyProductList(userId);
		mav.addObject("productList", productList);
		mav.setViewName("mypage");
		return mav;
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public ResponseEntity<String> registerBoard(@ModelAttribute("ProductVO") ProductVO productInformation, HttpSession session) {
		LOGGER.info(productInformation.toString());
		mainService.registerProduct(productInformation, session);

		return new ResponseEntity<String>("게시물 등록에 성공하셨습니다.", HttpStatus.OK);
	}

	@RequestMapping(value = "buy/{productId}", method = RequestMethod.POST)
	public ResponseEntity<String> processPurchase(@PathVariable("productId") int productId, HttpSession session) {
		LOGGER.info(productId+"번 물품을 구매하였습니다.");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("productId", productId);
		map.put("userId", (String)session.getAttribute("userId"));

		mainService.recordTransaction(map);
		return new ResponseEntity<String>("구매에 성공하셨습니다.", HttpStatus.OK);
	}
}
