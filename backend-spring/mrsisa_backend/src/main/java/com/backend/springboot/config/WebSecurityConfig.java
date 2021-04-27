package com.backend.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.backend.springboot.security.auth.RestAuthenticationEntryPoint;
import com.backend.springboot.security.auth.TokenAuthenticationFilter;
import com.backend.springboot.service.OsobaService;
import com.backend.springboot.util.TokenUtils;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private OsobaService osobaService;
	
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(osobaService)
			
			.passwordEncoder(passwordEncoder());
	}
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			
			.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
			
			.authorizeRequests().antMatchers("/auth/**").permitAll()   //ovde svi mogu da pristupe, todo dodati search lekova apoteka
								.antMatchers("/h2-console/**").permitAll()
								.antMatchers("/**").permitAll()
			
			.anyRequest().authenticated().and()
			 
			.cors().and()
			
			.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, osobaService), BasicAuthenticationFilter.class);
		
		http.csrf().disable();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.POST, "/auth/login");
		
		web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "favicon.ico", "/**/*.html",
				"/**/*.css", "/**/*.js");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
