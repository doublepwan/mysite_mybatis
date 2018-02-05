package com.javaex.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.UserDao;
import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user/loginform", method = RequestMethod.GET)
	public String loginForm() {

		System.out.println("로그인");
		return "user/loginform";
	}

	@RequestMapping("/user/joinform")
	public String joinForm() {
		System.out.println("회원가입");
		return "user/joinform";
	}

	@RequestMapping("/user/join")
	public String join(@ModelAttribute UserVo userVo) {

		userService.join(userVo);
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpSession session) {
		// System.out.println("email : " + email);
		// System.out.println("password : " + password);

		UserVo authUser = userService.login(email, password);
		if (authUser != null) {
			session.setAttribute("authUser", authUser);
			return "main/index";
		} else {
			return "redirect:/user/loginform?result=fail";
		}
	}
	
	@RequestMapping("/user/logout")
	public String logout(HttpSession session){
		//로그아웃 처리
		session.invalidate();
		return "redirect:/user/loginform";
	}

	@RequestMapping("/user/modifyform")
	public String modifyForm(HttpSession session, Model model) {

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		// no가 없으면 로그인 폼으로 보내버리기 redirect
		if (authUser == null) {// 로그인x
			return "redirect:/user/loginform";
		} else {// 로그인
			// 로그인 회원 no
			String no = authUser.getNo();
			//
			UserVo userVo = userService.getUser(no);
			model.addAttribute("userVo", userVo);
		}
		return "user/modifyform";
	}

	@RequestMapping("/user/modify")
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {

		System.out.println("수정되냐?");
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {// 로그인x
			return "redirect:/user/loginform";
		} else {// 로그인
			userService.update(userVo);
			session.setAttribute("authUser", userVo);
			
		}
		return "main/index";
	}
}
