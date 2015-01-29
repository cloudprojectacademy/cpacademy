package com.cpacademy.core.cpa.context;

import com.cpacademy.core.cpa.common.UserContext;

public class ContextHolder {

	// Defaults to ThreadLocalContextHolderStrategy.
	private static ContextHolderStrategy<UserContext> strategy = new ThreadLocalContextHolderStrategy<UserContext>();

	/**
	 * Getter for the UserContext from the ThreadLocal
	 * 
	 * @return - the current thread's UserContext
	 */
	public static UserContext getContext() {
		return strategy.getContext();
	}

	/**
	 * Setter for the UserContext - only test classes & sub-projects can set the context.
	 * 
	 * @param context
	 */
	protected static void setContext(UserContext context) {
		strategy.setContext(context);
	}

	/**
	 * This will clear the UserContext - only test classes & sub-projects can clear the context.
	 * 
	 * @return
	 */
	protected static UserContext clearContext() {
		return strategy.clearContext();
	}

	/**
	 * Changes the preferred strategy. Do <em>NOT</em> call this method more than once for a given JVM.
	 * 
	 * @param strategy
	 *            the ContextHolderStrategy instance you wish to use
	 */
	public static void setStrategy(ContextHolderStrategy<UserContext> strategy) {
		ContextHolder.strategy = strategy;
	}
}
