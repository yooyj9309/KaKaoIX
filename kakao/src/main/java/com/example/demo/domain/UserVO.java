package com.example.demo.domain;

public class UserVO {

	private String userId;
	private String userPw;
	private int coinAmount;
	private String checkPw;
	
	public String getCheckPw() {
		return checkPw;
	}

	public void setCheckPw(String checkPw) {
		this.checkPw = checkPw;
	}

	public int getCoinAmount() {
		return coinAmount;
	}

	public void setCoinAmount(int coinAmount) {
		this.coinAmount = coinAmount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	
	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", userPw=" + userPw + ", coinAmount=" + coinAmount + ", checkPw=" + checkPw + "]";
	}
}
