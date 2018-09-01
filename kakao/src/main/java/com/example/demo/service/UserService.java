package com.example.demo.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.mapper.UserMapper;
import com.example.demo.domain.UserVO;
import com.example.demo.exception.EmptyInputException;
import com.example.demo.exception.InvalidInputException;
import com.example.demo.exception.ServerErrorException;
import com.example.demo.utils.SecurityUtil;


@Service("com.example.demo.service.UserService")
public class UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserMapper userMapper;

	/**
	 * 사용자 입력정보를 통해 회원확인 로직 수행
	 * @param loginInformation
	 * @return
	 */
	public UserVO loginService(UserVO loginInformation) {
		String userId = loginInformation.getUserId().trim();
		String userPw = loginInformation.getUserPw().trim();

		if (StringUtils.isEmpty(userId)) {
			throw new EmptyInputException("ID를 입력해주세요.");
		}

		if (StringUtils.isEmpty(userPw)) {
			throw new EmptyInputException("비밀번호를 입력해주세요.");
		}

		String encryptPw = SecurityUtil.encryptSHA256(loginInformation.getUserId() + loginInformation.getUserPw());
		String resultId = null;

		loginInformation.setUserPw(encryptPw);
		try {
			resultId = userMapper.selectCheckInformation(loginInformation);
		} catch (DataAccessException e) {
			throw new ServerErrorException("서버 내부 문제입니다.");
		}

		if (StringUtils.isEmpty(resultId)) {
			throw new InvalidInputException("아이디나 비밀번호가 다릅니다.");
		}

		return loginInformation;
	}

	/**
	 * 회원가입 로직을 수행하기 위한 기능
	 * 비밀번호 체크, 아이디 중복 확인
	 * @param joinInformation
	 */
	public void joinService(UserVO joinInformation) {
		LOGGER.info(joinInformation.toString());
		String userId = joinInformation.getUserId().trim();
		String userPw = joinInformation.getUserPw().trim();
		String checkPw = joinInformation.getCheckPw().trim();

		if (StringUtils.isEmpty(userId)) {
			throw new EmptyInputException("ID를 입력해주세요.");
		}

		if (StringUtils.isEmpty(userPw)) {
			throw new EmptyInputException("비밀번호를 입력해주세요.");
		}

		if (StringUtils.isEmpty(checkPw)) {
			throw new EmptyInputException("비밀번호를  체크해주세요.");
		}

		if (!userPw.equals(checkPw)) {
			throw new InvalidInputException("비밀번호와 일치하지 않습니다.");
		}

		if (!isValidPattern(userId)) {
			throw new InvalidInputException("아이디 또는 비밀번호를 확인해주세요.");
		}

		joinInformation.setUserPw(SecurityUtil.encryptSHA256(userId + userPw));

		try {
			userMapper.insertNewUserInformation(joinInformation);
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw new ServerErrorException("이미 존재하는 아이디 입니다.");
		}

	}
	
	/**
	 * 아이디 패턴 체크 영문 6~20자
	 * @param input
	 * @return
	 */
	private boolean isValidPattern(String input) {
		Pattern pattern = Pattern.compile("^.*(?=.{6,20})(?=.*[a-zA-Z]).*$");
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			return true;
		} else {
			return false;
		}
	}

}