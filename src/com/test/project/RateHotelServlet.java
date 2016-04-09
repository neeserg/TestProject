package com.test.project;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class RateHotelServlet extends CustomServlet {

	@Override
	protected void handleRequest(HttpServletRequest req,
			HttpServletResponse resp) throws IOException, ServletException {
		
		
		
		RequestDispatcher requestDispatcher=req.getRequestDispatcher("/Welcome.jsp");
		requestDispatcher.forward(req, resp);
		
			
	}
	

}
