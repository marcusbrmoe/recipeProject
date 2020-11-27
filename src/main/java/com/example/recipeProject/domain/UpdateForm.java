package com.example.recipeProject.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UpdateForm {

	@NotEmpty
	private String token; 
	
	@NotEmpty
	@Size(min=7, max=30)
	private String password = "";

	@NotEmpty
	@Size(min=7, max=30)
	private String passwordCheck = "";

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}
	
	
}
