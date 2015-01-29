package com.cpacademy.service.cpa.user.impl;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.jboss.ejb3.annotation.Clustered;

import com.cpacademy.domain.cpa.user.entity.User;
import com.cpacademy.persistence.cpa.user.manager.UserManager;
import com.cpacademy.service.cpa.common.impl.AbstractServiceImpl;
import com.cpacademy.service.cpa.user.UserServiceLocal;
import com.cpacademy.service.cpa.user.UserServiceRemote;



@Stateless
@Remote({ com.cpacademy.service.cpa.user.UserServiceRemote.class })
@Local({ com.cpacademy.service.cpa.user.UserServiceLocal.class })
@Clustered
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UserServiceImpl extends AbstractServiceImpl implements UserServiceLocal, UserServiceRemote {
	
	@EJB
	private UserManager userManager;
	
	public User authenticateUser(String username, String password) {
		User user = this.userManager.authenticateUser(username, password);
		return user;
	}	
}
