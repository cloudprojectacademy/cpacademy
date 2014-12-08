package com.cpacademy.service.user;

import com.cpacademy.domain.user.entity.User;

public interface UserServiceRemote {

	public User authenticateUser(String username, String password);
}
