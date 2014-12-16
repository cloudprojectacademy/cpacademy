package com.cpacademy.mvc.myprojects;

import javax.servlet.http.HttpServletRequest;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cpacademy.domain.user.entity.User;
import com.cpacademy.service.user.UserComponent;

@Controller
@RequestMapping(value = "/pacademyhome")
public class HomeController {

	//private static Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	UserComponent userComponent;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getLoginForm(Model model) {
		return "Home";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String loginUser(HttpServletRequest request, Model model){
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try{
			System.out.println("Trying to authenticate the user. username:" + username + ", password:" + password);
			//logger.info("Trying to authenticate the user. username:" + username + ", password:" + password);
			User user = this.userComponent.authenticateUser(username, password);
			System.out.println("User is authenticated" + user.getUserId());
			//logger.info("User is authenticated" + user.getUserId());
		}catch(Throwable e){
			/**/
		}
		return "security/Login";
	}
}
