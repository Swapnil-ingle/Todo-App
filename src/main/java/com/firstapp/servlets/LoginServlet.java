package com.firstapp.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.firstapp.datasource.DbConnection;
import com.firstapp.domain.User;
import com.firstapp.services.LoginService;

@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		User user = new User(req.getParameter("name"), req.getParameter("password"));
		
		if (userIsValid(user, req)) {
			req.getSession().setAttribute("user", user);
			resp.sendRedirect("list-todo.do");
		} else {
			reportError(req, resp);
		}
	}
	
	private boolean userIsValid(User user, HttpServletRequest req) {
		try {
			return LoginService.isUserValid(user, (DbConnection) req.getSession().getAttribute("dbConnection"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	private void reportError(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setAttribute("error", "Invalid Credentials!");
		req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
	}
}
