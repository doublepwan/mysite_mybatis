package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;


@Repository
public class GuestbookDao { 
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestbookVo> getList() {
		
		List<GuestbookVo> list = sqlSession.selectList("guestbook.getList");
		return list;
	}
	public int insert(GuestbookVo vo) {
		
		 sqlSession.insert("guestbook.insert", vo);
		 return vo.getNo();
	}
	public int delete(GuestbookVo guestVo) { 
		                  
		return sqlSession.delete("guestbook.delete", guestVo);
	}
	
	public List<GuestbookVo> selectListByPage(int page){
		return sqlSession.selectList("guestbook.selectListByPage", page);
	}
	public GuestbookVo getOne(int no) {
		
		return sqlSession.selectOne("guestbook.getOne", no);
	}
	
}