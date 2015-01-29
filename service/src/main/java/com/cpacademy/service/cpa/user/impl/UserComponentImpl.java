package com.cpacademy.service.cpa.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cpacademy.domain.cpa.user.entity.User;
import com.cpacademy.service.cpa.common.impl.AbstractComponentImpl;
import com.cpacademy.service.cpa.user.UserComponent;
import com.cpacademy.service.cpa.user.UserServiceLocal;
import com.cpacademy.service.cpa.user.UserServiceRemote;

@Component
public class UserComponentImpl extends AbstractComponentImpl implements UserComponent  {

	private UserServiceLocal userServiceLocal;
	
	private UserServiceRemote userServiceRemote;
	
	public User authenticateUser(String username, String password) {
		User user = null;
		boolean useLocal = false;//ApplicationProperties.useLocalBean();

		if (useLocal) {
			this.userServiceLocal = (UserServiceLocal) this.getServiceLocalObject("userServiceLocal");
			user = this.userServiceLocal.authenticateUser(username, password);
		} else {
			user = this.userServiceRemote.authenticateUser(username, password);
		}
		return user;
	}	
	
	private UserServiceLocal getUserServiceLocal() {
		return (UserServiceLocal) this.getServiceLocalObject("userServiceLocal");
	}
	
	@Autowired
	public void setUserServiceRemote(UserServiceRemote userServiceRemote) {
		this.userServiceRemote = userServiceRemote;
	}

	public void setUserServiceLocal(UserServiceLocal userServiceLocal) {
		this.userServiceLocal = userServiceLocal;
	}	
	
}
