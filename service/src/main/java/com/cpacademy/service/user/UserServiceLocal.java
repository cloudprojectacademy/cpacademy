package com.cpacademy.service.user;

import com.cpacademy.domain.user.entity.User;

public interface UserServiceLocal {

	public User authenticateUser(String username, String password);
}
