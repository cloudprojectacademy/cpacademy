package com.cpacademy.persistence.cpa.util;

import com.cpacademy.core.cpa.util.StringUtil;

/***
 * This class will have all the util(support) static methods needed for the persistence layer classes.
 * 
 * @author amathew
 * 
 */
public class ManagerUtil {

	public static void validateNotNull(Class<?> entityClass, Object id) {
		if (id == null) {
			String className = StringUtil.lastToken(entityClass.getName(), ".");
			String entityName = StringUtil.lastToken(entityClass.getName(), ".");
			ManagerUtil.validateNotNull(className + ".get" + entityName + "(id)", id);
		}
	}

	public static void validateNotNull(Class<?> entityClass, Object entity, String methodName) {
		if (entity == null) {
			String className = StringUtil.lastToken(entityClass.getName(), ".");
			ManagerUtil.validateNotNull(className + "." + methodName, entity);
		}
	}

	public static void validateNotNull(String msg, Object argToCheck) {
		if (argToCheck == null) {
			throw new IllegalArgumentException("Argument not allowed to be null: " + msg);
		}
	}
}
