package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestBookService;

import com.javaex.vo.GuestBookVo;

@Controller

public class ApiGuestBookController {

	@Autowired

	GuestBookService guestBookService;

	@ResponseBody
	@RequestMapping("/guest/api/listajax")

	public List<GuestBookVo> apiList(@RequestParam("page") int page) {

		System.out.println(page);

		List<GuestBookVo> list = guestBookService.guestBookListPage(page);

		System.out.println(list.toString());

		return list;

	}

	@ResponseBody
	@RequestMapping("/guest/api/insertajax")

	public GuestBookVo apiInsert(@ModelAttribute GuestBookVo guestBookVo) {

		guestBookService.insertApi(guestBookVo);

		return guestBookService.getOne(guestBookVo);

	}

	@ResponseBody
	@RequestMapping("/guest/api/deleteajax")

	public int apiDeleteGuest(@ModelAttribute GuestBookVo guestBookVo) {

		guestBookService.deleteApi(guestBookVo);

		int no = guestBookVo.getNo();

		return no;

	}

}