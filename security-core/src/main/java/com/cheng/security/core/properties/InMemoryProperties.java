/**
 * 
 */
package com.cheng.security.core.properties;

import java.util.ArrayList;
import java.util.List;

import com.cheng.security.core.InMemoryUser;

/**
 * @author jack.lin
 *
 */
public class InMemoryProperties {
	
	private List<InMemoryUser> users = new ArrayList<>();

	public List<InMemoryUser> getUsers() {
		return users;
	}

	public void setUsers(List<InMemoryUser> users) {
		this.users = users;
	}
}
