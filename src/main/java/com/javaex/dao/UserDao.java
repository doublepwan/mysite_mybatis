package com.javaex.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlsession; 
	
	public UserVo selectUser(String email, String password) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("password", password);
		
		return sqlsession.selectOne("user.selectUserByEmailPw",map);
	}

	public void insert(UserVo userVo) {
		sqlsession.insert("user.insert", userVo);
		
	}

	public int update(UserVo userVo) {
		int affected = 0;
		affected = sqlsession.update("user.update",userVo); 
		return affected;
	}

	public UserVo selectUser(String no) {
		
		return sqlsession.selectOne("user.getOneUserByNo", no);
	}
	
	public UserVo emailCheck(String email) {
		UserVo userVo = sqlsession.selectOne("user.checkEmail", email);
		return userVo;
	}

}
