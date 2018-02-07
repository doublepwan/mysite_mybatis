package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public UserVo login(String email, String password) {
		
		return userDao.selectUser(email, password);
	}

	public void join(UserVo userVo) {
		userDao.insert(userVo);
		
	}

	public int update(UserVo userVo) {
		
		return userDao.update(userVo);
	}

	public UserVo getUser(String no) {
		
		return userDao.selectUser(no);
	}
	
	public boolean emailCheck(String email) {
		boolean result;
		UserVo userVo = userDao.emailCheck(email);
		if(userVo != null) {
			result = false;
		}
		else {
			result = true;
		}
		return result;
	}
	
}
