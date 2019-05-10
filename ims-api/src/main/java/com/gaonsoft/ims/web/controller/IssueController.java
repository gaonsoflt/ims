package com.gaonsoft.ims.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IssueController extends WebBaseController {
	
	@RequestMapping("/web/issues")
	public ModelAndView issues() {
		ModelAndView mav = getModelAndView("/issue/issues");
		return mav;
	}
	
	@RequestMapping("/web/issues2")
	public ModelAndView issues2() {
		ModelAndView mav = getModelAndView("/issue/issues2");
		return mav;
	}
}
