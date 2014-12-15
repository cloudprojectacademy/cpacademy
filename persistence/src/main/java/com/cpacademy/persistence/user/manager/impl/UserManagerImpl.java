package com.cpacademy.persistence.user.manager.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;

import com.cpacademy.domain.user.entity.User;
import com.cpacademy.persistence.common.manager.impl.PersistenceManagerImpl;
import com.cpacademy.persistence.user.manager.UserManager;

@Stateless
@Local({ com.cpacademy.persistence.user.manager.UserManager.class })
public class UserManagerImpl extends PersistenceManagerImpl implements UserManager {

	public User authenticateUser(String username, String password) throws SecurityException {
		User user = null;
		try {
			user = (User) this.getEntityManager().createNamedQuery("User.findUserByUsernameAndPassword").setParameter("username", username).setParameter("password", password).getSingleResult();

		} catch (Throwable ex) {
			throw new SecurityException("We are unable to authenticate the username/password. Please check the credetionals and try again.");
		}

		return user;
	}
	

	public User getUser(Long userId) {
		return this.find(User.class, userId);
	}	
}
