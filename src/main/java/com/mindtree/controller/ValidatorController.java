package com.mindtree.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mindtree.service.ValidationSevice;

@Controller
public class ValidatorController {

	@Autowired
	private ValidationSevice validationServiceImpl;

	@RequestMapping("/validateUser")
	public ModelAndView addNums(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		String result = validationServiceImpl.validateUser(userName, password);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("display");
		modelAndView.addObject("result", result);
		return modelAndView;
	}
}