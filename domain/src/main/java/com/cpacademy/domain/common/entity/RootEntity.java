package com.cpacademy.domain.common.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class RootEntity implements Serializable {

	private static final long serialVersionUID = -4029708234569785274L;

	public abstract Serializable getPK();
}
