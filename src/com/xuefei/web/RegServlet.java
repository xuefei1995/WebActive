package com.xuefei.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xuefei.entity.User;
import com.xuefei.service.UserService;
import com.xuefei.thread.SendMail;
import com.xuefei.util.WebUtil;

public class RegServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		//封装user对象
		User user=new User();
		user.setId(WebUtil.getUUID());
		user.setName(name);
		user.setPassword(password);
		user.setMail(mail);
		user.setCode(WebUtil.getUUID());
		//保存user到数据库
		UserService service=new UserService();
		service.save(user);
		//发送邮件到用户邮箱
		new Thread(new SendMail(user)).start();
		//重定向到注册成功页面
		response.sendRedirect(request.getContextPath()+"/RegSuccess.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
