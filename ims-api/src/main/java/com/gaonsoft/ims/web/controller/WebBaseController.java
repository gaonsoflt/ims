package com.gaonsoft.ims.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebBaseController {
	
	protected ModelAndView getModelAndView(String title, String viewName) {
		ModelAndView mav = new ModelAndView("common/view");
		mav.addObject("title", title);
		mav.addObject("page", viewName);
		return mav;
	}
	
	protected ModelAndView getModelAndView(String viewName) {
		return getModelAndView("IMS", viewName);
	}
}
