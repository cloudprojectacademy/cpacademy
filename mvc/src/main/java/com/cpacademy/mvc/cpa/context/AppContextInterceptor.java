package com.cpacademy.mvc.cpa.context;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.method.HandlerMethod;

import com.cpacademy.core.cpa.common.UserContext;
import com.cpacademy.core.cpa.context.AppContextHolder;
import com.cpacademy.mvc.cpa.common.CPAUtil;


public class AppContextInterceptor extends HandlerInterceptorAdapter {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(AppContextInterceptor.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (logger != null)
			logger.debug("preHandle.Determining the UserContext and setting to AppContext by the PVO Interceptor");

		try {
			UserContext userContext = this.determineUserContext(request);
			AppContextHolder.setContext(userContext);
			if (logger != null)
				logger.debug("preHandle.Determining the UserContext and setting to AppContext by the PVO Interceptor:" + userContext.getId());
			
			String requestMethod = request.getMethod();

		} catch (Throwable e) {
            RequestDispatcher rd = request.getRequestDispatcher("/management/adminportal/errorRedirect");
	        rd.forward(request, response);
			logger.error("We are unable to process this request as there is an error occured during the prehandle process of this request." + e);
			return false;
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object,
	 * org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception {

		if (logger != null)
			logger.debug("postHandle.Determining the UserContext and setting to AppContext by the PVO Interceptor" + AppContextHolder.getUserContext().getId());

		// Build the CPA Token and save it to to the View
		logger.info("postHandle. Trying to build the user token");
		CPAUtil.buildUserToken(request, mv);
		
		// Build the hidden variables and set it to the request so that we can output in the JSP.
		logger.info("postHandle. Trying to set the cpa fields");
		CPAUtil.setCPAFields(request, mv);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object,
	 * java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception arg3) throws Exception {
		if (logger != null)	logger.debug("Clearing the UserContext from the AppContext by the PVO Interceptor");

		AppContextHolder.clearContext();
	}

	/**
	 * This method will build the UserContext for this request and return it.
	 * 
	 * @param request
	 *            the request
	 * @return the user context
	 */
	private UserContext determineUserContext(HttpServletRequest request) {

		UserContext uc = null;
		String language = null, country = null, name = null, category = null;

		// Look for userContext as a form variable.
		String ucString = request.getParameter("user_context");

		// If param found, reconstitute UserContext from it.
		if (ucString != null && ucString.length() > 0 && ucString.length() < 2000) {

			/* If userContext data found, reconstitute UC object. */
			uc = UserContext.fromToken(ucString, null);

			/* If UC resolved, resolve various information. */
			if (uc != null) {

				/* Determine/assign SubAgencyCode reference (which may have been changed). */
				uc.setSubAgencyCode(request.getParameter("agency"));

				/* Get the various currently assigned country, language, name, etc. information. */
				language = uc.getLanguageCode();
				country = uc.getCountryCode();
				name = uc.getName();
				category = uc.getCategory();
			}
		}

		/* If UserContext wasn't resolved, then resolve it now. */
		if (uc == null) {
			/* Determine external origin either from domain name or querystring parameter. */
			String extOrigin = this.determineExternalOrigin(request);

			// Determine SubAgencyCode which is required for resolving default language, etc.
			String subAgencyCode = this.determineSubAgencyCode(request);

			/* Create a partially complete UC. Remaining attributes (language, etc.) are set below. */
			uc = new UserContext(extOrigin, null, subAgencyCode);
		}

		/* Assign the default language, country, name and user category to the UserContext object. */
		if ((language = this.getLanguageCode(request, language)) != null)
			uc.setLanguageCode(language);
		if ((country = this.getCountryCode(request, country)) != null)
			uc.setCountryCode(country);
		if ((name = this.getUserName(request, name)) != null)
			uc.setName(name);
		if ((category = this.getUserCategory(request, category)) != null)
			uc.setCategory(category);

		uc.setCurrentDomain((String)request.getHeader("host"));
		
		return uc;
	}

	// This method determines the SubAgencyCode. Subclasses can override this method (if desired).
	/**
	 * Determine sub agency code.
	 * 
	 * @param request
	 *            the request
	 * @return the string
	 */
	private String determineSubAgencyCode(HttpServletRequest request) {

		String subAgencyCode = request.getParameter("agency");
		return subAgencyCode;
	}

	/**
	 * Gets the language code.
	 * 
	 * @param request
	 *            the request
	 * @param currentLanguage
	 *            the current language
	 * @return the language code
	 */
	private String getLanguageCode(HttpServletRequest request, String currentLanguage) {
		String language = null;
		String requestedLanguage = request.getParameter("langcode");

		/* If the requestedLanguage is null and a currencyLanguage is assigned, then it becomes the requested language. */
		if (requestedLanguage == null && currentLanguage != null)
			requestedLanguage = currentLanguage;

		/* If a specific (non-default) language was requested, then determine if its allowed. */
		if (requestedLanguage != null && requestedLanguage.length() > 0) {
			language = requestedLanguage;
		} else {
			language = "en";
		}

		return language;
	}

	/**
	 * If we have a "countrycode" parameter in the request, we return it else return the default country code.
	 * 
	 * @param request
	 *            the request
	 * @param currentCountryCode
	 *            the current country code
	 * @return - the countrycode
	 */
	private String getCountryCode(HttpServletRequest request, String currentCountryCode) {
		String requestedCountry = request.getParameter("countrycode");

		if (requestedCountry == null || requestedCountry.length() == 0) {
			return requestedCountry;
		} else {
			return (currentCountryCode == null || currentCountryCode.length() == 0 ? "US" : currentCountryCode);
		}
	}

	/**
	 * Gets the user name.
	 * 
	 * @param request
	 *            the request
	 * @param currentUsername
	 *            the current username
	 * @return the user name
	 */
	private String getUserName(HttpServletRequest request, String currentUsername) {
		String name = request.getParameter("name");
		if (name == null || name.length() == 0)
			name = currentUsername;

		return name;
	}

	/**
	 * Gets the user category.
	 * 
	 * @param request
	 *            the request
	 * @param currentCategory
	 *            the current category
	 * @return the user category
	 */
	private String getUserCategory(HttpServletRequest request, String currentCategory) {
		String category = request.getParameter("user_category");
		if (category == null)
			category = currentCategory;
		if (category == null)
			category = UserContext.SERVLET_USER;

		return category;
	}

	/**
	 * This will determine the external origin of this request.
	 * 
	 * @param request
	 *            the request
	 * @return the string
	 */
	private String determineExternalOrigin(HttpServletRequest request) {
		String origin = request.getParameter("ref_domain");
		return origin;
	}

}
