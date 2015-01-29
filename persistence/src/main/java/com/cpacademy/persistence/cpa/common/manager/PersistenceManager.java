package com.cpacademy.persistence.cpa.common.manager;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface PersistenceManager {

	public <T> T persist(T entity);

	public <T> T find(Class<T> entityClass, Object id);

	public <T> T merge(T entity);

	public void remove(Object entity);

	public void flush();

	public SessionFactory getHibernateSessionFactory();

	public Session getHibernateSession(EntityManager entityManager);

	public EntityManager getEntityManager();

}
