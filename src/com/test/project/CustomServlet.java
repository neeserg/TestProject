package com.test.project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		handleRequest(req,resp);

	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handleRequest(req,resp);

	}

	protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

	
	}


}
