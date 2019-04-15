package com.gaonsoft.ims.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@RequestMapping("/web/login")
	public ModelAndView issues(Model model) {
		ModelAndView mav = new ModelAndView("login/login");
		
		return mav;
	}
}
