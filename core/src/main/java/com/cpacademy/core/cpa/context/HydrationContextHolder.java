package com.cpacademy.core.cpa.context;

import com.cpacademy.core.cpa.common.HydrationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HydrationContextHolder {

	private static ContextHolderStrategy<HydrationContext> strategy = new ThreadLocalContextHolderStrategy<HydrationContext>();
	private static Logger logger = LoggerFactory.getLogger(HydrationContextHolder.class);

	/**
	 * Getter for the HydrationContext
	 * 
	 * @return - the HydrationContext for the current Thread
	 */
	public static HydrationContext getHydrationContext() {
		return getHydrationContext(false);
	}

	/**
	 * 
	 * @param createIfNecessary
	 *            - tells it is necessary to create a new HydrationContext or not.
	 * @return - the HydrationContext from the current Thread
	 */
	public static HydrationContext getHydrationContext(boolean createIfNecessary) {
		HydrationContext context = (HydrationContext) strategy.getContext();
		if (context == null && createIfNecessary == true) {
			context = new HydrationContext();
			setHydrationContext(context);
		}
		return context;
	}

	/**
	 * Setter for the HydrationContext
	 * 
	 * @param context
	 */
	public static void setHydrationContext(HydrationContext context) {
		strategy.setContext(context);
	}

	/**
	 * This will clear the HydrationContext from the current Thread Local Context.
	 * 
	 * @return
	 */
	public static HydrationContext clearContext() {
		return (HydrationContext) strategy.clearContext();
	}

}
