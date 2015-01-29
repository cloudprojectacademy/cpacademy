package com.cpacademy.service.cpa.user;

import com.cpacademy.domain.cpa.user.entity.User;

public interface UserComponent {

	public User authenticateUser(String username, String password);
}
