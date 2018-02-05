package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestbookVo;

@Controller
public class GuestbookController {

	@Autowired
	private GuestBookService guestService;
	
	@RequestMapping("/guestbook/list")
	public String list(Model model) {
		List<GuestbookVo> list = guestService.getList();
		model.addAttribute("gList", list);
		return "guestbook/list";
	}
	@RequestMapping("/guestbook/insert")
	public String add(@ModelAttribute GuestbookVo vo) {
		guestService.insert(vo);
		return"redirect:/guestbook/list";
	}
	@RequestMapping("/guestbook/deleteform")
	public String deleteform(@RequestParam("no") String no, Model model) {
		model.addAttribute("no", no);
		return"/guestbook/deleteform";
	}
	@RequestMapping("/guestbook/delete")
	public String delete(@ModelAttribute GuestbookVo guestBookVo) {
		guestService.delete(guestBookVo.getNo(), guestBookVo.getPassword());
		return"redirect:/guestbook/list";
	}
}