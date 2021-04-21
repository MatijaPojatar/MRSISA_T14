package com.backend.springboot.dto;

public class OsobaTokenState {
	private String accessToken;
    private Long expiresIn;

    public OsobaTokenState() {
        this.accessToken = null;
        this.expiresIn = null;
    }

    public OsobaTokenState(String accessToken, long expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
    
}
