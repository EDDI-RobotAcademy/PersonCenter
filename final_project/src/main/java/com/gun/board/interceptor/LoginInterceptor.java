package com.gun.board.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// �α��� ���ͼ��� �α��μ��� ��������
		
		HttpSession session = request.getSession();
		String loginid = (String) session.getAttribute("loginid");
		String str = "";
		if (loginid == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			str += "<script> alert('�α��� �ϼ���');";
			str += "location.href='" + request.getContextPath() + "/'; </script>";
			// System.out.println("LoginInterceptor is working..");
			// response.sendRedirect(request.getContextPath() + "/customer/login");
			out.println(str);
			out.flush();
			return false;
		}
		return super.preHandle(request, response, handler);
	}

}
