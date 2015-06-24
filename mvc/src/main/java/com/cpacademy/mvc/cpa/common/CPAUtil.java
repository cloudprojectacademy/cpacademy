package com.cpacademy.mvc.cpa.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

public class CPAUtil {

	public static void buildUserToken(HttpServletRequest request, ModelAndView mv) {
		
		// Return if the mv is null
		if (mv == null || mv.getModel() == null) return;
		
		//UserToken userToken = new UserToken();
		
	}
}
