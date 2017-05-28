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
		//��װuser����
		User user=new User();
		user.setId(WebUtil.getUUID());
		user.setName(name);
		user.setPassword(password);
		user.setMail(mail);
		user.setCode(WebUtil.getUUID());
		//����user�����ݿ�
		UserService service=new UserService();
		service.save(user);
		//�����ʼ����û�����
		new Thread(new SendMail(user)).start();
		//�ض���ע��ɹ�ҳ��
		response.sendRedirect(request.getContextPath()+"/RegSuccess.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
