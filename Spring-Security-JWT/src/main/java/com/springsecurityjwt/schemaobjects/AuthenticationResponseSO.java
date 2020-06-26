package com.springsecurityjwt.schemaobjects;

public class AuthenticationResponseSO {

	private String jwt;

	public AuthenticationResponseSO() {

	}

	public AuthenticationResponseSO(String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

}
