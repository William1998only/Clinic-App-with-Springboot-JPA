package com.lawencon.klinik.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lawencon.klinik.service.UsersServiceImpl;

/**
 * 
 * @author WILLIAM
 *
 */
@EnableWebSecurity
public class ApiSecurity extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private ApiSecurityServiceImpl apiSecurityImpl;
	
	@Autowired
	private UsersServiceImpl userServiceImpl;
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncode() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.cors().and().csrf().disable().authorizeRequests()
		.anyRequest().authenticated();
		http.addFilter(new AuthenticationFilter(super.authenticationManager(), userServiceImpl));
	
		http.addFilter(new AuthorizationFilter(super.authenticationManager()));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth){
		try {
			auth.userDetailsService(apiSecurityImpl).passwordEncoder(bCryptPasswordEncode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override 
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers(HttpMethod.POST, "/users");
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/api/login")
//				.allowedOrigins("http://localhost:4200")
//				.allowedMethods(HttpMethod.POST.name());
//			}
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedOrigins("http://localhost:4200")
				.allowedMethods(HttpMethod.POST.name(), HttpMethod.GET.name()
						, HttpMethod.PATCH.name(), HttpMethod.PUT.name()
						, HttpMethod.DELETE.name());
			}
		};
	}
}
