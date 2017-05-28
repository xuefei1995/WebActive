package com.xuefei.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xuefei.entity.User;
import com.xuefei.service.UserService;
import com.xuefei.util.WebUtil;

public class ActServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code=request.getParameter("code");
		UserService service=new UserService();
		User user = service.checkCode(code);
		if(user!=null){
			//判断是否超过24时
			if(WebUtil.checkTime(user)){
				service.updateTag(code);
				request.setAttribute("msg", "恭喜您,激活成功!");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
			}else {
				request.setAttribute("msg", "激活时间已过,请重新注册");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
			}
		}else{
			request.setAttribute("msg", "用户不存在!");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
