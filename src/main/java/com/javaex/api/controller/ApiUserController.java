package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
public class ApiUserController {
	
	@Autowired
	private UserService userService;
	
	
	
	@ResponseBody
	@RequestMapping("/user/api/emailCheck")
		public boolean emailCheck(@RequestParam("email") String email) {
		System.out.println(email);
		boolean result = userService.emailCheck(email);
		System.out.println(result);
		
		return true;
	}
}
