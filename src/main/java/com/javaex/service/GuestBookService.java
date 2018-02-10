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

	public int delete(GuestbookVo guestbookVo) {
		return guestDao.delete(guestbookVo);
	}

	public int insert(GuestbookVo vo) {
		return guestDao.insert(vo);
	}
	public List<GuestbookVo> guestBookListPage(int page){
		return guestDao.selectListByPage(page);
	}


	public GuestbookVo getOne(int no) {
		
		return guestDao.getOne(no);
	}

	public GuestbookVo insertApi(GuestbookVo guestBookVo) {
		int no = guestDao.insert(guestBookVo);
				
		return guestDao.getOne(no);
		
	}
	
}
