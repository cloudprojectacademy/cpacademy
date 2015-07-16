package com.cpacademy.mvc.cpa.common;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.cpacademy.core.cpa.common.Constants;
import com.cpacademy.core.cpa.common.UserContext;
import com.cpacademy.core.cpa.common.UserToken;
import com.cpacademy.core.cpa.context.AppContextHolder;
import com.cpacademy.domain.cpa.user.entity.User;
import com.cpacademy.mvc.cpa.myprojects.HomeController;

public class CPAUtil {

	private static Logger logger = LoggerFactory.getLogger(CPAUtil.class);
	
	/** The Constant hiddenFieldHTML1. */
	public static final String hiddenFieldHTML1 = "<input type=\"hidden\" name=\"";

	/** The Constant hiddenFieldHTML2. */
	public static final String hiddenFieldHTML2 = "\" id=\"";

	/** The Constant hiddenFieldHTML2. */
	public static final String hiddenFieldHTML3 = "\" value=\"";

	/** The Constant hiddenFieldHTML3. */
	public static final String hiddenFieldHTML4 = "\">" + Constants.TO_STRING_ATTR_DELIM;	
	
	public static void buildUserToken(HttpServletRequest request, ModelAndView mv) {

		User user = (User) mv.getModel().get("user");
		
		// Return if the mv is null
		if (mv == null || mv.getModel() == null || user == null) return;
		
		UserToken userToken = new UserToken();
		userToken.setLoggedInUserid(user.getUserId());
		userToken.setLoggedInUsername(user.getUsername());
		
		mv.getModel().put("usertoken", userToken);
	}
	
	public static void setCPAFields(HttpServletRequest request, ModelAndView mv) {
		if (mv == null || mv.getModel() == null)
			return;

		StringBuilder fields = new StringBuilder();
		try {

			UserToken token = (UserToken) mv.getModel().get("usertoken");
			String temp = token != null ? token.getToToken() : null;
			if (temp != null && temp.length() > 0) {
				fields.append(CPAUtil.hiddenFieldHTML1 + "cpa_tokendata" + CPAUtil.hiddenFieldHTML2 + "cpa_tokendata" + CPAUtil.hiddenFieldHTML3 + temp + CPAUtil.hiddenFieldHTML4);
			}

			UserContext uc = AppContextHolder.getUserContext();
			temp = uc != null ? uc.toToken() : null;

			if (temp != null && temp.length() > 0) {
				fields.append(CPAUtil.hiddenFieldHTML1 + "user_context" + CPAUtil.hiddenFieldHTML2 + "user_context" + CPAUtil.hiddenFieldHTML3 + temp + CPAUtil.hiddenFieldHTML4);
			}

		} catch (Throwable e) {
			logger.error("AppContextInterceptor.setCPAFields encountered an error while setting the CPA hidden fields.", e);
		}

		mv.getModel().put("cpafields", fields.toString());
	}	
}
