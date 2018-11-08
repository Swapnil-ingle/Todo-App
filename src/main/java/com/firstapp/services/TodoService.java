package com.firstapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.firstapp.domain.Todo;

public class TodoService {
	private static List<Todo> todos = new ArrayList<>();
	
	static {
		todos.add(new Todo("Web App Dev", "Study"));
		todos.add(new Todo("Learn MVC", "Study"));
		todos.add(new Todo("Go shopping", "Fun"));
	}
	
	public static List<Todo> getAllTodos() {
		return todos; 
	}
	
	public static void addTodo(String name, String category) {
		Todo todo = new Todo(name, category);
		todos.add(todo);
	}

	public static void removeTodo(String todoId) {
		List<Todo> deleteTodos = todos.stream()
			.filter(todo -> todo.getId() == Integer.parseInt(todoId))
			.collect(Collectors.toList());
		
		deleteTodos.forEach(todo -> todos.remove(todo));
	}
	
}
