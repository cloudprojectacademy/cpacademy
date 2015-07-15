package com.cpacademy.mvc.cpa.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.cpacademy.core.cpa.common.UserToken;
import com.cpacademy.domain.cpa.user.entity.User;

public class CPAUtil {

	public static void buildUserToken(HttpServletRequest request, ModelAndView mv) {

		User user = (User) mv.getModel().get("user");
		
		// Return if the mv is null
		if (mv == null || mv.getModel() == null || user == null) return;
		
		UserToken userToken = new UserToken();
		userToken.setLoggedInUserid(user.getUserId());
		userToken.setLoggedInUsername(user.getUsername());
		
		mv.getModel().put("usertoken", userToken);
	}
}
