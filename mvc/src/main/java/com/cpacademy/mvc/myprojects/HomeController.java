package com.cpacademy.mvc.myprojects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/pacademyhome")
public class HomeController {

	@RequestMapping(method = RequestMethod.GET)
	public String getLoginForm(Model model) {
		return "Home";
	}

}
