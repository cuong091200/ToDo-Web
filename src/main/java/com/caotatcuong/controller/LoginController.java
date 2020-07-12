package com.caotatcuong.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.caotatcuong.service.ILoginService;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Inject
	ILoginService loginService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("views/login.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if (loginService.checkLogin(username, password)) {
			RequestDispatcher rd = req.getRequestDispatcher("views/todo-list.jsp");
			rd.forward(req, resp);
		}else {}
	}
}
