package com.xuefei.dao;

import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.xuefei.entity.User;
import com.xuefei.util.JdbcUtil;


public class UserDao {
	
	//�����û������ݿ�
	public void save(User user){
		QueryRunner qr=new QueryRunner(JdbcUtil.getDataSource());
		String sql="INSERT INTO user_list VALUES (?,?,?,?,?,?,?)";
		Object[] obj = {
				user.getId(),
				user.getName(),
				user.getPassword(),
				user.getMail(),
				0,
				user.getCode(),
				new Date()
		};
		try {
			qr.update(sql, obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//ͨ��code��ȡ�û�
	public User getUserByCode(String code){
		QueryRunner qr=new QueryRunner(JdbcUtil.getDataSource());
		String sql="SELECT * FROM user_list WHERE CODE=?";
		Object[] obj={code};
		try {
			return (User)qr.query(sql, new BeanHandler(User.class), obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//����tag��ֵ
	public void updateTag(String code){
		QueryRunner qr=new QueryRunner(JdbcUtil.getDataSource());
		String sql="UPDATE user_list SET tag=1 WHERE CODE=?";
		Object[] obj={code};
		try {
			qr.update(sql, obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
