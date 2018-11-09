package com.firstapp.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import com.firstapp.datasource.DbConnection;
import com.firstapp.domain.User;

public class LoginService {
	public static boolean isUserValid(User user, DbConnection dbConnection) throws SQLException {
		Connection conn = dbConnection.getConn();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement("SELECT Password FROM USERS WHERE name = ?");
			stmt.setString(1, user.getName());
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				if (BCrypt.checkpw(user.getPassword(), rs.getString("Password"))) {
					return true;
				}
			} 
			
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
		}
		
		return false;
	}
	
	public static void registerNewUser(String username, String password, DbConnection dbConnection) throws SQLException {
		Connection conn = dbConnection.getConn();
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement("INSERT INTO Users(name, password) VALUES (?,?)");
			stmt.setString(1, username);
			stmt.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
		}
	}
}
