package com.firstapp.domain;

public class Todo {
	private String name;
	
	private String category;
	
	private static int incrementalId;
	
	private int id;
	
	public Todo(String name, String category) {
		super();
		this.name = name;
		this.category = category;
		incrementalId++;
		this.id = incrementalId;
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
