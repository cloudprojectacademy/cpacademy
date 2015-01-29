package com.cpacademy.core.cpa.common;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;


public class HydrationContext implements Serializable {

	private static final long serialVersionUID = -4029708234569785274L;

	private Collection<Enum<?>> enums = new HashSet<Enum<?>>();

	public HydrationContext() {

	}

	/**
	 * 
	 * @param anEnum
	 *            - the enum which needs to be added to the collection of enums.
	 * @return the HydrationContext.
	 */
	public HydrationContext add(Enum<?> anEnum) {
		if (anEnum != null) {
			this.enums.add(anEnum);
		}
		return this;
	}

	/**
	 * 
	 * @param anEnum
	 *            - the enum which needs to checked against the current collection of enums
	 * @return - true if the passed enum is on the collection of enums.
	 */
	public boolean contains(Enum<?> anEnum) {
		return this.enums.contains(anEnum);
	}

	/**
	 * 
	 * @return - the current collection of enums.
	 */
	public Collection<Enum<?>> getEnums() {
		return enums;
	}

}
