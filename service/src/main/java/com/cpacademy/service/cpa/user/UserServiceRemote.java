package com.cpacademy.service.cpa.user;

import com.cpacademy.domain.cpa.user.entity.User;

public interface UserServiceRemote {

	public User authenticateUser(String username, String password);
}
