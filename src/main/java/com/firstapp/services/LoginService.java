package com.firstapp.services;

public class LoginService {
	public static boolean isUserValid(String name, String password) {
		if (name.equals("admin") && password.equals("Login@123")) {
			return true;
		}
		
		return false;
	}
}
