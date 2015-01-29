package com.cpacademy.core.cpa.exception;

public class ObjectNotFoundException extends PersistenceException {

	private static final long serialVersionUID = -4029708234569785274L;

	private Object id;
	private Class<?> entityClass;

	public ObjectNotFoundException(Class<?> entityClass, Object id) {
		this.id = id;
		this.entityClass = entityClass;
	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public Class<?> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<?> entityClass) {
		this.entityClass = entityClass;
	}
}
