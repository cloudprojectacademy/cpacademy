package com.cpacademy.persistence.common.manager.impl;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.ejb.EntityManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cpacademy.core.exception.ObjectNotFoundException;
import com.cpacademy.core.exception.ProcessingException;
import com.cpacademy.domain.common.entity.RootEntity;
import com.cpacademy.persistence.common.manager.PersistenceManager;
import com.cpacademy.persistence.util.ManagerUtil;


@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class PersistenceManagerImpl implements PersistenceManager {

	private static Logger logger = LoggerFactory.getLogger(PersistenceManagerImpl.class);

	private EntityManager entityManager;

	private SessionFactory sessionFactory;

	public PersistenceManagerImpl() {
		// Default Constructor
	}

	@Autowired
	public PersistenceManagerImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * This method will use the EntityManager to persist the passed entity object.
	 * 
	 * @param Generic
	 *            Type object which holds any entity object
	 * 
	 * @return the newly persisted entity object
	 */
	public <T> T persist(T entity) {
		ManagerUtil.validateNotNull(this.getClass(), entity, "persist()");
		entityManager.persist(entity);
		return entity;
	}

	/**
	 * This method will use the EntityManager to update/merge the passed entity in the DB.
	 * 
	 * @param Generic
	 *            Type object which holds any entity object
	 * 
	 * @return the updated entity object
	 */
	public <T> T merge(T entity) {
		ManagerUtil.validateNotNull(this.getClass(), entity, "merge()");
		return this.entityManager.merge(entity);
	}

	/**
	 * This method will use the EntityManager to remove the passed entity from the DB.
	 * 
	 * @param An
	 *            Object entity
	 * 
	 */
	public void remove(Object entity) {
		ManagerUtil.validateNotNull(this.getClass(), entity, "remove()");
		entityManager.remove(entity);
	}

	public void flush() {
		entityManager.flush();
	}

	@PersistenceContext(unitName = "CPA-EntityManager")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	/**
	 * This method will find the entity from the database for the passed entityClass and primary key(id).
	 * 
	 * @param - the entityClass which is used to find the entity from the database.
	 * @param - the id of the passed entityClass
	 * 
	 * @return the generic type T which holds the entity.
	 */
	public <T> T find(Class<T> entityClass, Object id) {
		ManagerUtil.validateNotNull(entityClass, id);

		T entity = null;
		Throwable caught = null;
		try {
			entity = entityManager.find(entityClass, id);
		} catch (Throwable t) {
			caught = t;
		}

		if (entity == null) {
			String msg = "PersistenceManagerImpl.find(" + entityClass + ", " + id + "), entity not found.";
			Throwable throwable = caught == null && logger.isDebugEnabled() ? new Throwable("Dummy For Stacktrace") : caught;
			logger.error(msg, throwable);
			throw new ObjectNotFoundException((Class<?>) entityClass, id);
		}
		return entity;
	}

	public <T> List findAll(Class<T> entity) {
		Query q = entityManager.createQuery("select e from " + entity.getSimpleName() + " e");
		return q.getResultList();
	}

	public <T> List findAll(Class<T> entity, String orderByClause, String sortType) {
		Query q = entityManager.createQuery("select e from " + entity.getSimpleName() + " e order by e." + orderByClause + " " + sortType);
		return q.getResultList();
	}

	public SessionFactory getHibernateSessionFactory() {
		return this.getHibernateSession(entityManager).getSessionFactory();
	}

	public Session getHibernateSession(EntityManager entityManager) {
		Session session = null;
		Object o = entityManager.getDelegate();
		if (o instanceof Session) {
			session = (Session) o;
		} else if (o instanceof EntityManagerImpl) {
			session = ((EntityManagerImpl) o).getSession();
		} else {
			String msg = "PersistenceManagerimpl.getHibernateSession() unable to get hibernate session from " + o.getClass().getName() + ".";
			logger.error(msg);
			throw new ProcessingException(msg);
		}
		return session;
	}

	protected void evictEntityAssociation(RootEntity rootEntity, String childAssociationName) {
		try {
			this.getHibernateSessionFactory().evictCollection(rootEntity.getClass().getName() + "." + childAssociationName, rootEntity.getPK());
		} catch (Throwable t) {
			String msg = "PersistenceManagerImpl.evictEntityAssociation() encountered an error. parentEntity = " + rootEntity + " childAssociationName = " + childAssociationName + ".  Error being supressed, allowing processing to continue.";
			logger.error(msg, t);
		}
	}
	
	protected void evictEntity(Class entityClass) {
		try {
			this.getHibernateSessionFactory().evict(entityClass);
		} catch (Throwable t) {
			String msg = "PersistenceManagerImpl.evictEntity() encountered an error. parentEntity = " + entityClass.getName() + ".  Error being supressed, allowing processing to continue.";
			logger.error(msg, t);
		}
	}	

	/**
	 * Business API to evict/flush cached queries.
	 */
	protected void evictCachedQueries(String queryCacheRegion) {
		try {
			// Flush any pending inserts, updates or deletes.
			//
			this.flush();
			// Now evict cached queries. Why flush first? So the unit of work is closer
			// to completion, and has less of a chance of re-caching before the "container"
			// commit. 
			this.getHibernateSessionFactory().evictQueries();
			if (logger.isDebugEnabled()) logger.debug("PersistenceManagerImpl.evictCachedQueries() evicted QueryCacheRegion=" + queryCacheRegion);
		}
		catch(Throwable t) {
			String msg = "PersistenceManagerImpl.evictCachedQueries() encountered an error. QueryCacheRegion=" + queryCacheRegion + ". Error being suspressed, allowing processing to continue.";
			logger.error(msg, t);
		}
	}	
}
