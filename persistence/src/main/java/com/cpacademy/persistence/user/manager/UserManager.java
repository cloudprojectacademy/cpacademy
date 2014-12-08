package com.cpacademy.persistence.user.manager;

import com.cpacademy.domain.user.entity.User;

public interface UserManager {

	public User authenticateUser(String username, String password) throws SecurityException;
}
