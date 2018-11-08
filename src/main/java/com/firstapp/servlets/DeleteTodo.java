package com.firstapp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.firstapp.services.TodoService;

@WebServlet(urlPatterns = "/delete-todo.do")
public class DeleteTodo extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		TodoService.removeTodo(req.getParameter("todoId"));
		resp.sendRedirect("list-todo.do");
	}
}
