package com.gaonsoft.ims.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController extends WebBaseController {
	
	@RequestMapping("/web/main")
	public ModelAndView main() {
		ModelAndView mav = getModelAndView("/main");
		return mav;
	}
}
