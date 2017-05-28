package com.xuefei.service;

import com.xuefei.dao.UserDao;
import com.xuefei.entity.User;

public class UserService {
	UserDao dao=new UserDao();
	public void save(User user){
		dao.save(user);
	}
	public User checkCode(String code){
		User user = dao.getUserByCode(code);
		return user;
	}
	public void updateTag(String code){
		dao.updateTag(code);
	}
}
