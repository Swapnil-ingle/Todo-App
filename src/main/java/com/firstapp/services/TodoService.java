package com.firstapp.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import com.firstapp.datasource.DbConnectionManager;
import com.firstapp.domain.Todo;
import com.firstapp.domain.User;

public class TodoService {
	private static List<Todo> todos;
	
	public static List<Todo> getAllTodos(HttpSession httpSession) throws SQLException {
		User user = (User) httpSession.getAttribute("user");
		DbConnectionManager dbConnManager = (DbConnectionManager) httpSession.getAttribute("dbConnection");
		List<Todo> todosInSession = (List<Todo>) httpSession.getAttribute("todos");
		
		if (todosInSession == null) {
			todos = new ArrayList<>();
			return initTodosList(httpSession);
		} else if (!newTodosAdded(dbConnManager.getConn(), user.getId(), todos)) {
			return todosInSession;
		} 
		
		return getAllTodos(user, dbConnManager);
	}
	
	public static List<Todo> initTodosList(HttpSession httpSession) throws SQLException {
		User user = (User) httpSession.getAttribute("user");
		DbConnectionManager dbConnManager = (DbConnectionManager) httpSession.getAttribute("dbConnection");
		
		return getAllTodos(user, dbConnManager);
	}
	
	public static List<Todo> getAllTodos(User user, DbConnectionManager dbConnManager) throws SQLException {
		Connection conn = dbConnManager.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement("SELECT Identifier, Todo, Category FROM todos WHERE user_id = ?");
			stmt.setInt(1, user.getId());
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				if (todoAlreadyPresent(rs.getInt("Identifier"), todos)) {
					continue;
				}
				todos.add(new Todo(rs.getString("Todo"), rs.getString("Category"), rs.getInt("Identifier")));
			}
			
			return todos;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {stmt.close();};
			if (rs != null) {rs.close();};
		}
		
		return todos;
	}
	
	private static boolean newTodosAdded(Connection conn, int userId, List<Todo> todos) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM todos WHERE user_id = ?");
		stmt.setInt(1, userId);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		if (rs.getInt(1) == todos.size()) {
			stmt.close();
			return false;
		}
		
		stmt.close();
		return true;
	}

	public static void addTodo(String name, String category, HttpSession session) throws SQLException {
		DbConnectionManager dbConnectionManager = (DbConnectionManager) session.getAttribute("dbConnection");
		User user = (User) session.getAttribute("user");
		List<Todo> todos = (List<Todo>) session.getAttribute("todos");
		
		addTodo(name, category, dbConnectionManager, user, todos);
	}
	
	public static void addTodo(String name, String category, 
			DbConnectionManager dbConnectionManager, User user, List<Todo> todos) 
			throws SQLException {
		Connection conn = dbConnectionManager.getConn();
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement("INSERT INTO todos(Todo, Category, User_Id) VALUES (?, ?, ?)");
			stmt.setString(1, name);
			stmt.setString(2, category);
			stmt.setString(3, Integer.toString(user.getId()));
			stmt.execute();
			
			Todo todo = new Todo(name, category, user.getId());
			
			todos.add(todo);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {stmt.close();};
		}
	}

	public static void removeTodo(String todoId, HttpSession session) throws SQLException {
		DbConnectionManager dbConnManager = (DbConnectionManager) session.getAttribute("dbConnection");
		
		Connection conn = dbConnManager.getConn();
		PreparedStatement stmt = null;
		int id = Integer.parseInt(todoId);
		
		try {
			stmt = conn.prepareStatement("DELETE FROM todos WHERE IDENTIFIER = ?");
			stmt.setInt(1, id);
			stmt.execute();
			
			removeById(session, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {stmt.close();};
		}
	}
	
	private static void removeById(HttpSession session, int id) {
		List<Todo> todos = (List<Todo>) session.getAttribute("todos");
		Todo todo = getExistingTodoById(id, todos);
		
		todos.remove(todo);
	}
	
	private static boolean todoAlreadyPresent(int todoId, List<Todo> todos) {
		if (getExistingTodoById(todoId, todos) != null) {
			return true;
		}
		
		return false;
	}
	
	private static Todo getExistingTodoById(int todoId, List<Todo> todos) {
		List<Todo> existingTodo = todos.stream()
				.filter(todo -> todo.getId() == todoId)
				.collect(Collectors.toList());
		
		if (existingTodo.size() > 0) {
			return existingTodo.get(0);
		}
		
		return null;
	}

	private static List<Todo> getExistingTodosById(List<Integer> todoIds, List<Todo> todos) {
		return todos.stream()
		.filter(todo -> todoIds.contains(todo.getId()))
		.collect(Collectors.toList());
	}
}
