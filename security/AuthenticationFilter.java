package com.lawencon.klinik.security;

import java.io.IOException;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.klinik.model.Users;
import com.lawencon.klinik.service.UsersServiceImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * 
 * @author WILLIAM
 *
 */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private UsersServiceImpl userServiceImpl; 

	public AuthenticationFilter(AuthenticationManager authenticationManager, UsersServiceImpl userServiceImpl) {
		this.authenticationManager = authenticationManager;
		this.userServiceImpl = userServiceImpl;
		super.setFilterProcessesUrl("/api/login");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		Users user = new Users();
		try {
			user = new ObjectMapper().readValue(request.getInputStream(), Users.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String username = authResult.getName();
		Users user = new Users();
		try {
			user = userServiceImpl.getUserByName(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String secretKey = "$2a$10$QPCwu8nYd3LYUMi.lDV1VeEeIsA62t5H9wiGccEKN/CEs7XALkdza";
		SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
		String token = Jwts.builder().signWith(key).setSubject(authResult.getName())
				.setExpiration(new Date(new Date().getTime()+50000000))
				.compact();
		response.setContentType("application/json");
		String newRes = String.format("{\"token\": \"%s\", \"role_code\": \"%s\", \"user_id\": \"%s\"}", 
				token, user.getId(), user.getIdRole().getRoleCode());
		System.out.println(user.getIdRole().getRoleCode() + "ksokoakosa");
		response.getWriter().append(newRes);
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
	}
	
}
