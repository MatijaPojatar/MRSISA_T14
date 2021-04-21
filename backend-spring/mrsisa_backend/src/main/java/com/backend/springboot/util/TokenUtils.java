package com.backend.springboot.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.backend.springboot.domain.Osoba;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtils {

	@Value("mrsisa_backend")
	private String APP_NAME;
	
	@Value("ultimate_secret")
	public String SECRET;
		
	//Period vazenja 30 minuta
	@Value("1800000")
	private int EXPIRES_IN;
	
	@Value("Authorization")
	private String AUTH_HEADER;
	
	private static final String AUDIENCE_WEB = "web";
	
	private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
	
	public String generateToken(String mail) { 
		return Jwts.builder()
				.setIssuer(APP_NAME)
				.setSubject(mail)
				.setAudience(AUDIENCE_WEB)
				.setIssuedAt(new Date())
				.setExpiration(generateExpirationDate())
				.signWith(SIGNATURE_ALGORITHM, SECRET).compact();
	}
	
	private Date generateExpirationDate() {
		return new Date(new Date().getTime() + EXPIRES_IN);
	}

	
	public String getToken(HttpServletRequest request) {
		String authHeader = getAuthHeaderFromHeader(request);
		// JWT se prosledjuje kroz header 'Authorization' u formatu:
				// Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9....
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}
		return null;
	}
	
	
	
	
	private Claims getAllClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token)
					.getBody();
		}catch (ExpiredJwtException exc) {
			throw exc;
		} catch(Exception e) {
			claims = null;
		}
		return claims;
	}
	
	
	public String getMailFromToken(String token) {
		String mail;
		
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			mail = claims.getSubject();
		}catch (ExpiredJwtException exc) {
			throw exc;
		}catch(Exception e) {
			mail = null;
		}
		return mail;	
	}
	
	public Date getIssuedAtDateFromToken(String token) {
		Date issueAt;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			issueAt = claims.getIssuedAt();
		}catch (ExpiredJwtException exc) {
			throw exc;
		}catch(Exception e) {
			issueAt = null;
		}
		return issueAt;
	}
	
	public String getAudienceFromToken(String token) {
		String audience;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			audience = claims.getAudience();
		}catch (ExpiredJwtException exc) {
			throw exc;
		} catch(Exception e) {
			audience = null;
		}
		return audience;
	}
	
	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			expiration = claims.getExpiration();
		}catch(ExpiredJwtException exc) {
			throw exc;
		} catch(Exception e) {
			expiration = null;
		}
		return expiration;
	}
	
	
	private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
		return (lastPasswordReset != null && created.before(lastPasswordReset));
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		Osoba osoba = (Osoba) userDetails;
		final String mail = getMailFromToken(token);
		final Date created = getIssuedAtDateFromToken(token);
		
		return (mail!= null
				&& mail.equals(userDetails.getUsername())//vraca mail ipak, pogledati implementaciju
				&& !isCreatedBeforeLastPasswordReset(created, osoba.getLastPasswordResetDate())); 
	}
	
	public int getExpiredIn() {
		return EXPIRES_IN;
	}
	
	public String getAuthHeaderFromHeader(HttpServletRequest request) {
		return request.getHeader(AUTH_HEADER);
	}
	
	
	
	
	
	
}
