package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;

	public List<BoardVo> getList() {
		
		return boardDao.getList();
	}

	public BoardVo getOne(String no) {
		
		return boardDao.getOne(no);
	}

	public void hitCount(String no) {
		boardDao.hitCount(no);
	}

	public void insert(BoardVo boardVo) {
		
		boardDao.insert(boardVo);
	}

	public void update(BoardVo boardVo) {
		boardDao.update(boardVo);
	}

	public void delete(String no) {
		boardDao.delete(no);
	}


}
