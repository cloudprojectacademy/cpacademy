package com.cpacademy.core.cpa.context;

import com.cpacademy.core.cpa.common.UserContext;

/**
 * This is the AppContext holder class which is called by the user to set any object (via ThreadLocal) which is needed for the current thread's lifecycle.
 * 
 * @author Anil Mathew
 * @version %I%, %G%
 * @since 1.0
 * 
 */
public class AppContextHolder {

	private static ContextHolderStrategy<UserContext> strategy = new ThreadLocalContextHolderStrategy<UserContext>();

	/**
	 * Getter for the UserContext
	 * 
	 * @return - the UserContext for the current Thread
	 */
	public static UserContext getUserContext() {
		return getUserContext(false);
	}

	/**
	 * 
	 * @param createIfNecessary
	 *            - tells it is necessary to create a new context or not.
	 * @return - the UserContext from the current Thread
	 */
	public static UserContext getUserContext(boolean createIfNecessary) {
		UserContext context = (UserContext) strategy.getContext();
		if (context == null && createIfNecessary == true) {
			context = new UserContext();
			setContext(context);
		}
		return context;
	}

	/**
	 * Setter for the UserContext
	 * 
	 * @param context
	 */
	public static void setContext(UserContext context) {
		strategy.setContext(context);
	}

	/**
	 * This will clear the UserContext from the current Thread Local Context.
	 * 
	 * @return
	 */
	public static UserContext clearContext() {
		return (UserContext) strategy.clearContext();
	}
}
