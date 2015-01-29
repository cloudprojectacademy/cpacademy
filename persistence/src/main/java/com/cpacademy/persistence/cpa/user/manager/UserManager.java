package com.cpacademy.persistence.cpa.user.manager;

import com.cpacademy.domain.cpa.user.entity.User;

public interface UserManager {

	public User authenticateUser(String username, String password) throws SecurityException;
}
