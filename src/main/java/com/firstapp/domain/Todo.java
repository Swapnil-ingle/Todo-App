package com.firstapp.domain;

public class Todo {
	private String name;
	
	private String category;
	
	private int id;
	
	public Todo(String name, String category, int id) {
		super();
		this.name = name;
		this.category = category;
		this.id = id;
	}
	
	public Todo(String name, String category) {
		this.name = name;
		this.category = category;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}

	public int getId() {
		return id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
