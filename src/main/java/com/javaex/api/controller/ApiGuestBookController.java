package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestbookVo;

@Controller
public class ApiGuestBookController {
	
	@Autowired
	GuestBookService guestBookService;
	
	@ResponseBody
	@RequestMapping("/guest/api/listajax")
	public List<GuestbookVo> apiList(@RequestParam("page") int page) {
		System.out.println(page);
		List<GuestbookVo> list = guestBookService.guestBookListPage(page);
		System.out.println(list.toString());
		return list;
	}
	
}
