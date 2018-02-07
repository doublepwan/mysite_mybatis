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
	
	
	final static int pageSize=5;
	final static int blockPage=2;
	
	@RequestMapping("/board/list")
	public String list(HttpServletRequest req, @RequestParam Map map, 
					   @RequestParam(defaultValue = "1", required = false) int nowPage ,Model model) {
		/*
		 * 페이징 순서
		 * 1. 전체 목록용 쿼리를 구간쿼리로 변경
		 * 2. 전체 레코드수 얻기용 메소드 추가
		 * 3. 페이징 로직을  컨트롤러에 추가
		 * 4. 리스트.JSP페이지에 결과값 출력
		 */
		
		//페이징을 위한 로직 시작
		//전체 레코드 수
		int totalRecordCount = boardService.getTotalCount(map);
		//전체 페이지 수
		int totalPage = (int)Math.ceil((double)totalRecordCount/pageSize);
		//시작 및 끝 rownum 구하기
		int start = (nowPage-1) * pageSize;
		int end = nowPage * pageSize;
		map.put("start", start);
		map.put("end", end);
		//페이징 로직 끝
		List<BoardVo> list = boardService.getList(map);
		//페이징용 서비스 호출
		String paging = PagingUtil.pagingText(totalRecordCount, start, end, nowPage, req.getContextPath()+"/board/list?");
		model.addAttribute("bList", list);
		model.addAttribute("paging", paging);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalCount", totalRecordCount);
		model.addAttribute("pageSize", pageSize);
		return "board/list";
	}
//	@RequestMapping("/board/list")
//	public String list(Model model) {
//		
//		List<BoardVo> list = boardService.getList();
//		model.addAttribute("bList", list);
//		return "board/list";
//	}
	
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
