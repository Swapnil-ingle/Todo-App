package com.firstapp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.firstapp.services.TodoService;

@WebServlet(urlPatterns = "/list-todo.do")
public class ListTodo extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		req.setAttribute("todos", TodoService.getAllTodos());
		req.getRequestDispatcher("/WEB-INF/views/list-todo.jsp").forward(req, resp);
	}
}
