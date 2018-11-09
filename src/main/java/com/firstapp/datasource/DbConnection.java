package com.firstapp.datasource;

import java.sql.Connection;

public class DbConnection {
	private Connection conn;

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
}
