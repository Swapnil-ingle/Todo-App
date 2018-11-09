package com.firstapp.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbServices {
	
	public static Connection initDbConnection() throws FileNotFoundException {
		try {
			Properties props = new Properties();
			FileInputStream in = new FileInputStream(getTomcatConfPath() + File.separatorChar + "/todo.properties");
			props.load(in);
			in.close();
			
			String driver = props.getProperty("jdbc.driver");
			if (driver != null) {
			    Class.forName(driver) ;
			}

			String url = props.getProperty("jdbc.url");
			String username = props.getProperty("jdbc.username");
			String password = props.getProperty("jdbc.password");

			return DriverManager.getConnection(url, username, password);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;	
	}

	private static String getTomcatConfPath() {
		return System.getProperty("catalina.base") + File.separatorChar + "/conf";
	}
}
