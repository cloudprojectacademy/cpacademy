package com.cpacademy.core.cpa.context;


public interface ContextHolderStrategy<T extends Object> {
	public T getContext();

	public void setContext(T context);

	public T clearContext();
}
