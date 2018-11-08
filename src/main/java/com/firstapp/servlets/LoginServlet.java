package com.firstapp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.firstapp.services.LoginService;

@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		
		if (LoginService.isUserValid(name, password)) {
			req.getSession().setAttribute("name", name);
			resp.sendRedirect("/list-todo.do");
		} else {
			reportError(req, resp);
		}
	}

	private void reportError(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setAttribute("error", "You seem lost!");
		req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
	}
}
