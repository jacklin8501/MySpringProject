/**
 * 
 */
package com.cheng.security.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cheng.security.core.properties.SecurityProperties;

/**
 * @author jack.lin
 *
 */
@Configuration
@EnableConfigurationProperties(value=SecurityProperties.class)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	SecurityProperties security;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
		System.out.println(":: okokok");
		InMemoryUserDetails userDetailsService = new InMemoryUserDetails();
		userDetailsService.setInMemory(security.getInMemory());
		userDetailsService.setPasswordEncoder(passwordEncoder);
		return userDetailsService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
				.antMatchers("/sign-in.html/**", "/signin/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/sign-in.html")
				.loginProcessingUrl("/signin")
				.and()
			.httpBasic();
	}
	
	
}
