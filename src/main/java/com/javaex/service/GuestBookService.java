package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestBookService {
	
	@Autowired
	private GuestbookDao guestDao;

	public List<GuestbookVo> getList() {
		
		return guestDao.getList();
	}

	public void delete(int no, String password) {
		guestDao.delete(no, password);
	}

	public void insert(GuestbookVo vo) {
		guestDao.insert(vo);
	}
	
}
