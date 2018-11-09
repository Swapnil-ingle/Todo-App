package com.firstapp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.firstapp.datasource.DbConnectionManager;
import com.firstapp.services.TodoService;

@WebServlet(urlPatterns = "/delete-todo.do")
public class DeleteTodo extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		deleteTodo(req, req.getSession());
		
		resp.sendRedirect("list-todo.do");
	}

	private void deleteTodo(HttpServletRequest req, HttpSession session) {
		try {
			TodoService.removeTodo(req.getParameter("todoId"), session);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
