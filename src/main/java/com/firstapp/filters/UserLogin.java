package com.firstapp.filters;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.firstapp.datasource.DbConnection;
import com.firstapp.services.DbServices;

@WebFilter(urlPatterns = "*.do")
public class UserLogin implements Filter {
	private DbConnection dbConn;
	
	@Override
	public void destroy() {
		try {
			this.dbConn.getConn().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) req;
		
		if (httpReq.getSession().getAttribute("dbConnection") == null) {
			httpReq.getSession().setAttribute("dbConnection", dbConn);
		}
		
		if (httpReq.getSession().getAttribute("user") != null) {
			chain.doFilter(req, resp);
		} else if (httpReq.getRequestURI().contains("/register-user.do")) {
			httpReq.getRequestDispatcher("/register-user.do").forward(req, resp);
		} else {
			httpReq.getRequestDispatcher("/login.do").forward(req, resp);
		}
	}
	
	@Override
	public void init(FilterConfig chain) throws ServletException {
		if (dbConn == null) {
			dbConn = new DbConnection();
		} else {
			return;
		}
		
		try {
			dbConn.setConn(DbServices.initDbConnection());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
