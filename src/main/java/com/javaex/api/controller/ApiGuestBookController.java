package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;

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

	@ResponseBody
	@RequestMapping("/guest/api/insertajax")

	public GuestbookVo apiInsert(@ModelAttribute GuestbookVo guestBookVo) {

		GuestbookVo guestVo = guestBookService.insertApi(guestBookVo);
		return guestVo;

	}

	@ResponseBody
	@RequestMapping("/guest/api/deleteajax")

	public int apiDeleteGuest(@ModelAttribute GuestbookVo guestBookVo) {

		int count = guestBookService.delete(guestBookVo);
		System.out.println(count);
		if (count != 0) { // DB삭제 성공 PK값을 보낸다.
			return guestBookVo.getNo();
		} else { // 실패시 -1 을 보낸다.
			return -1;
		}

	}

}