package com.firstapp.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.firstapp.services.TodoService;

@WebServlet(urlPatterns = "/add-todo.do")
public class AddTodo extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/add-todo.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		try {
			TodoService.addTodo(req.getParameter("todo"), req.getParameter("category"), req.getSession());
		} catch (SQLException e) {
			e.getMessage();
		}
		resp.sendRedirect("list-todo.do");
	}
}
