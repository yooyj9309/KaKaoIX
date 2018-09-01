package com.example.demo.mapper;

import com.example.demo.domain.UserVO;

public interface UserMapper {

	void insertNewUserInformation(UserVO userInformation);

	String selectCheckInformation(UserVO loginInformation);
	
	String selectIdCheck(UserVO loginInformation);
	
}
