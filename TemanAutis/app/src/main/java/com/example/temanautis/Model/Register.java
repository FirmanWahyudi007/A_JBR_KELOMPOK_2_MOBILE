package com.example.temanautis.Model;

import com.google.gson.annotations.SerializedName;

public class Register {

	@SerializedName("user")
	private RegisterData registerData;

	@SerializedName("token")
	private String token;

	public void setRegisterData(RegisterData registerData){
		this.registerData = registerData;
	}

	public RegisterData getRegisterData(){
		return registerData;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}
}