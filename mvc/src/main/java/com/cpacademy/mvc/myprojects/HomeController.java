package com.cpacademy.mvc.myprojects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cpacademy.service.user.UserComponent;

@Controller
@RequestMapping(value = "/pacademyhome")
public class HomeController {

	@Autowired
	UserComponent userComponent;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getLoginForm(Model model) {
		return "Home";
	}

	public String loginUser(HttpServletRequest request, Model model){
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try{
			this.userComponent.authenticateUser(username, password);
		}catch(Throwable e){
			/**/
		}
		return "security/Login";
	}
}
