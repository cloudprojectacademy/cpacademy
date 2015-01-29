package com.cpacademy.domain.cpa.user.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.NamedQuery;
import com.cpacademy.domain.cpa.common.entity.RootEntity;

@NamedQueries({ @NamedQuery(name = "User.findUserByUsernameAndPassword", query = "select user from User user where user.username = :username and user.password = :password") })
@Entity
@Table(name = "User")
public class User extends RootEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "sequenceId", sequenceName = "USER_SEQQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceId")
	@Column(name = "USER_ID")
	private Long userId;
	
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "PASSWORD")
	private String password;
	
	public User() {
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}	
	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	@Transient
	public Serializable getPK() {
		return this.userId;
	}
		
}
