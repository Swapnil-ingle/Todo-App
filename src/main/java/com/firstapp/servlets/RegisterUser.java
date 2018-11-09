package com.firstapp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.firstapp.datasource.DbConnectionManager;
import com.firstapp.services.DbServices;
import com.firstapp.services.LoginService;
import com.firstapp.services.TodoService;

@WebServlet(urlPatterns = "/register-user.do")
public class RegisterUser extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/register-user.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		try {
			LoginService.registerNewUser(req.getParameter("name"), req.getParameter("password"), 
					(DbConnectionManager) req.getSession().getAttribute("dbConnection"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect("login.do");
	}
}
