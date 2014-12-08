package com.cpacademy.service.common.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class AbstractComponentImpl {

	public Object getServiceLocalObject(String serviceLocalName) {

		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(getConfigLocations());
		return appContext.getBean(serviceLocalName);
	}

	private static String[] getConfigLocations() {
		return new String[] { "*-local.xml" };
	}
}
