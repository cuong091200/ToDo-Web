package com.caotatcuong.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.caotatcuong.model.User;
import com.caotatcuong.service.IUserService;

@WebServlet(urlPatterns = "/register")
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Inject
	IUserService userService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("views/register.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUsername(username);
		user.setPassword(password);
		user = userService.insert(user);
		if (user != null) {
			req.setAttribute("NOTIFICATION", "User Registered Successfully!");
		}
		else {
			req.setAttribute("NOTIFICATION", "User Registered Unsuccessfully!");
		}
		resp.sendRedirect("/ToDoApp/register");
//		RequestDispatcher rd = req.getRequestDispatcher("views/register.jsp");
//		rd.forward(req, resp);
	}
}
