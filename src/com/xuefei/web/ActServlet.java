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
			//�ж��Ƿ񳬹�24ʱ
			if(WebUtil.checkTime(user)){
				service.updateTag(code);
				request.setAttribute("msg", "��ϲ��,����ɹ�!");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
			}else {
				request.setAttribute("msg", "����ʱ���ѹ�,������ע��");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
			}
		}else{
			request.setAttribute("msg", "�û�������!");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
