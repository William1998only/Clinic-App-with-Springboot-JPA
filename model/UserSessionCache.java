package com.lawencon.klinik.model;

import org.springframework.stereotype.Component;

/**
 * 
 * @author WILLIAM
 *
 */

@Component
public class UserSessionCache {
	
	private Users activeUser;

	public Users getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(Users activeUser) {
		this.activeUser = activeUser;
	}

}
