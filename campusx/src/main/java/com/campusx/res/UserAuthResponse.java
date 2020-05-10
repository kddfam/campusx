package com.campusx.res;

public class UserAuthResponse {
	
	private final String jwt;
	
	public UserAuthResponse(String jwt) {
		this.jwt = jwt;
	}
	
	public String getJwt() {
		return jwt;
	}

}
