package com.javaex.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.service.PagingUtil;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	

	@RequestMapping("/board/list")
	public String list(Model model) {
		
		List<BoardVo> list = boardService.getList();
		model.addAttribute("bList", list);
		return "board/list";
	}
	
	@RequestMapping("/board/view")
	public String view(@ModelAttribute BoardVo boardVo, Model model) {
		String no = boardVo.getNo();
		boardVo = boardService.getOne(no);
		model.addAttribute("boardVo", boardVo);
		boardVo.setContent(boardVo.getContent().replace("\r\n", "<br/>"));
		boardService.hitCount(no);
		return "board/view";
	}
	
	@RequestMapping("/board/writeForm")
	public String writeForm() {
		return "board/write";
	}
	
	@RequestMapping("/board/write")
	public String write(@ModelAttribute BoardVo boardVo, HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		boardVo.setUserNo(authUser.getNo());
		boardVo.setName(authUser.getName());
		boardService.insert(boardVo);
		return "redirect:/board/list";
	}
	
	@RequestMapping("/board/modifyForm")
	public String modifyForm(@RequestParam("no") String no, Model model, @ModelAttribute BoardVo boardVo) {
		model.addAttribute("no", no);
		boardVo = boardService.getOne(no);
		model.addAttribute("boardVo", boardVo);
		return "board/modify";
	}
	
	@RequestMapping("/board/modify")
	public String modify(@ModelAttribute BoardVo boardVo) {
		
		boardService.update(boardVo);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping("/board/delete")
	public String delete(@RequestParam("no") String no, Model model) {
		
		model.addAttribute("no", no);
		boardService.delete(no);
		return "redirect:/board/list";
	}
}
