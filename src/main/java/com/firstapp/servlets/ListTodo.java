package com.firstapp.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.firstapp.datasource.DbConnectionManager;
import com.firstapp.domain.Todo;
import com.firstapp.domain.User;
import com.firstapp.services.TodoService;

@WebServlet(urlPatterns = "/list-todo.do")
public class ListTodo extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		req.setAttribute("todos", getTodos(req.getSession()));
		req.getRequestDispatcher("/WEB-INF/views/list-todo.jsp").forward(req, resp);
	}
	
	private List<Todo> getTodos(HttpSession httpSession) {
		try {
			List<Todo> todos = TodoService.getAllTodos(httpSession);
			httpSession.setAttribute("todos", todos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
