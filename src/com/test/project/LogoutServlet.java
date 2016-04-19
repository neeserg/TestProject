package com.test.project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class LogoutServlet extends CustomServlet {

	@Override
	protected void handleRequest(HttpServletRequest req,
			HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession(false);
	if(session != null){
		session.invalidate();
		resp.sendRedirect("/home");
	}
	
	
	}
	

}
