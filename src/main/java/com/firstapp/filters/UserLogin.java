package com.firstapp.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = "*.do")
public class UserLogin implements Filter {
	
	@Override
	public void destroy() {
		
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) req;
		if (httpReq.getSession().getAttribute("name") != null) {
			chain.doFilter(req, resp);
		} else {
			httpReq.getRequestDispatcher("/login.do").forward(req, resp);
		}
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
