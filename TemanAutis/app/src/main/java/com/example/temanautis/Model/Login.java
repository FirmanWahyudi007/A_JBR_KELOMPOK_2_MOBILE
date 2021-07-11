package com.example.temanautis.Model;

import com.google.gson.annotations.SerializedName;

public class Login{

	@SerializedName("user")
	private LoginData loginData;

	@SerializedName("token")
	private String token;

	public void setLoginData(LoginData loginData){
		this.loginData = loginData;
	}

	public LoginData getLoginData(){
		return loginData;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}
}