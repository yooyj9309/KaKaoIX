package com.example.demo.controller;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.UserVO;
import com.example.demo.service.UserService;



@RestController
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	/**
	 * 로그인 화면
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView loginView = new ModelAndView();
		loginView.setViewName("login");
		return loginView;
	}

	/**
	 * 로그인 내부 로직 처리
	 * @param loginInformation
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(@ModelAttribute("UserVO") UserVO loginInformation, HttpSession session) {
		
		UserVO user = userService.loginService(loginInformation);
		session.setAttribute("userId", user.getUserId());
		
		return new ResponseEntity<String>("로그인에 성공하셨습니다.", HttpStatus.OK);
	}
	
	/**
	 * 회원가입 로직 수행
	 * @param joinInformation
	 * @return 성공 시 메시지 출력
	 */
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public ResponseEntity<String> join(@ModelAttribute("UserVO") UserVO joinInformation) {
		LOGGER.info(joinInformation.toString());
		
		userService.joinService(joinInformation);
		
		LOGGER.info("JOIN SERVICE 로직 성공");
		return new ResponseEntity<String>("회원가입에 성공하셨습니다.", HttpStatus.OK);
	}

	/**
	 * 로그아웃 로직 수행 후 login 화면으로 redirect
	 * 
	 * @param session, sessionStatus
	 * @return logoutView
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView processLogout(HttpSession session,SessionStatus sessionStatus) {
		session.invalidate();
		sessionStatus.setComplete();
		
		ModelAndView logoutView = new ModelAndView();
		logoutView.setViewName("login");
		return logoutView;
	}
	
}
