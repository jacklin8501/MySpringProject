/**
 * 
 */
package com.cheng.security.core;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cheng.security.core.properties.InMemoryProperties;

/**
 * @author jack.lin
 *
 */
public class InMemoryUserDetails implements UserDetailsService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private PasswordEncoder passwordEncoder;
	private InMemoryProperties inMemory;
	
	public InMemoryUserDetails() {
	}

	public InMemoryUserDetails(InMemoryProperties inMemory) {
		this.inMemory = inMemory;
	}

	public InMemoryUserDetails(InMemoryProperties inMemory, PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
		this.inMemory = inMemory;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info(":: InMemoryUserDetails.loadUserByUsername({})");
		List<InMemoryUser> users = inMemory.getUsers();
		if (null != users && !users.isEmpty()) {
			for (InMemoryUser user : users) {
				if (StringUtils.equalsIgnoreCase(username, user.getUsername())) {
					if (null != passwordEncoder) {
						return new User(username, passwordEncoder.encode(user.getPassword()), AuthorityUtils.createAuthorityList(user.getRole()));
					} else {
						return new User(username, user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole()));
					}
				}
			}
		}
		return null;
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public InMemoryProperties getInMemory() {
		return inMemory;
	}

	public void setInMemory(InMemoryProperties inMemory) {
		this.inMemory = inMemory;
	}
}
