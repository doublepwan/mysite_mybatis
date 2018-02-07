package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;

	public List<BoardVo> getList(Map map) {
		List<BoardVo> list = sqlSession.selectList("board.getList",map);
		return list;
	}

	public BoardVo getOne(String no) {
		
		return sqlSession.selectOne("board.getOne", no);
	}

	public void hitCount(String no) {
		sqlSession.update("board.hitUp", no);
	}

	public void insert(BoardVo boardVo) {
		sqlSession.insert("board.insert", boardVo);
	}

	public void update(BoardVo boardVo) {
		sqlSession.update("board.update", boardVo);
	}

	public void delete(String no) {
		sqlSession.delete("board.delete", no);
	}

	public int getTotalCount(Map map) {
		return sqlSession.selectOne("board.getTotalCount", map);
	}
	
}
