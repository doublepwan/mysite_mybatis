package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestBookVo;

@Service
public class GuestBookService {
	
	@Autowired
	private GuestbookDao guestDao;

	public List<GuestBookVo> getList() {
		
		return guestDao.getList();
	}

	public void delete(int no, String password) {
		guestDao.delete(no, password);
	}

	public void insert(GuestBookVo vo) {
		guestDao.insert(vo);
	}
	public List<GuestBookVo> guestBookListPage(int page){
		return guestDao.selectListByPage(page);
	}

	public void deleteApi(GuestBookVo guestBookVo) {
		guestDao.insert(guestBookVo);
		
	}

	public GuestBookVo getOne(GuestBookVo guestBookVo) {
		
		return guestDao.getOne(guestBookVo);
	}

	public void insertApi(GuestBookVo guestBookVo) {
		guestDao.insert(guestBookVo);
		
	}
	
}
