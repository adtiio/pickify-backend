package com.pickify.config;

import java.util.Date; 

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {
		
//		public static final String SECTET_KEY="adiffffffffffffffffffffffffffffffffffffffffffffffffffffsafafasjkhdfkashdflkjhasjlkdfhffffffffffftya";
//		public static final String JWT_HEADER="Authorization";
	
	JwtConstant jwtConstant=new JwtConstant();
	
	
		
	SecretKey key=Keys.hmacShaKeyFor(jwtConstant.SECTET_KEY.getBytes());
	
	public String generateToken (Authentication auth) {
		String jwt=Jwts.builder()
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime()+84600000))
				.claim("email", auth.getName())
				.signWith(key).compact();
		
		return jwt;
	}
	public String getEmailFromToken(String jwt) {
		jwt=jwt.substring(7);
		
		Claims claims=Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
		
		String email=String.valueOf(claims.get("email"));
		
		return email;
		
	}
}
