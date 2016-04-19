package com.test.project;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class HomeServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		handleRequest(req,resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handleRequest(req,resp);
	}

	private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession(false);
		
		if (session != null && session.getAttribute("login") != null && (String) session.getAttribute("login") =="logged_in"){
			
				RequestDispatcher requestDispatcher=req.getRequestDispatcher("/Welcome.jsp");
				requestDispatcher.forward(req, resp);
			
		}
		
		else{
			RequestDispatcher requestDispatcher=req.getRequestDispatcher("/index.html");
			requestDispatcher.forward(req, resp);
		}
		
		

	
		
	}

}
