package com.backend.springboot.dto;

public class JwtAuthenticationRequest {
	
	private String mail;
	private String password;
	
	
	public JwtAuthenticationRequest() {
		super();
	}
	public JwtAuthenticationRequest(String mail, String password) {
		this.mail = mail;
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setEmail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
