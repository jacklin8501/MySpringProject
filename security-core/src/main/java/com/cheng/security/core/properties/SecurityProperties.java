/**
 * 
 */
package com.cheng.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author jack.lin
 *
 */
@ConfigurationProperties(prefix="cheng.security")
public class SecurityProperties {

	private InMemoryProperties inMemory = new InMemoryProperties();

	public InMemoryProperties getInMemory() {
		return inMemory;
	}

	public void setInMemory(InMemoryProperties inMemory) {
		this.inMemory = inMemory;
	}
}
