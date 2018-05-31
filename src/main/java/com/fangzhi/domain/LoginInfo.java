package com.fangzhi.domain;

public class LoginInfo{

    private Boolean remember = false;
    private User user;
	/**
	 * @return the remember
	 */
	public Boolean getRemember() {
		return remember;
	}
	/**
	 * @param remember the remember to set
	 */
	public void setRemember(Boolean remember) {
		this.remember = remember;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
}