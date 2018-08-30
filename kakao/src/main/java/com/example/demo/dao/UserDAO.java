package com.example.demo.dao;

import com.example.demo.domain.UserVO;

public interface UserDAO {
	void insertNewUserInformation(UserVO userInformation);

	String selectCheckInformation(UserVO loginInformation);
	
	UserVO selectMyInfo(String userId);
}
