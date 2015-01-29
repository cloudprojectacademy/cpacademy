package com.cpacademy.core.cpa.context;

/**
 * ThreadLocal implementation of the PVO ContextHolderStrategy
 */
public class ThreadLocalContextHolderStrategy<T extends Object> implements ContextHolderStrategy<T> {

	private ThreadLocal<T> contextHolder = new ThreadLocal<T>();

	public T clearContext() {
		T t = contextHolder.get();
		contextHolder.set(null);
		return t;
	}

	public T getContext() {
		return contextHolder.get();
	}

	public void setContext(T context) {
		contextHolder.set(context);
	}
}
