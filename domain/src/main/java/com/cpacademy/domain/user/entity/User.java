package com.cpacademy.domain.user.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cpacademy.domain.common.entity.RootEntity;

@Entity
@Table(name = "USER")
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
