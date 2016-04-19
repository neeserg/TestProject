package com.test.project;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyEmailServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handleRequest(req,resp);
	}
	
	protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if (req.getParameter("email")==null ||req.getParameter("emailHash") ==null )
		{
			RequestDispatcher requestDispatcher=req.getRequestDispatcher("/emailSent.jsp");
			requestDispatcher.forward(req, resp);
		}
		else{
			String email = req.getParameter("email");
			String emailHash = req.getParameter("emailHash");
			UserService userService = new UserService();
			UserExist userExist = userService.Check_email(email);
			if(userExist.isCheck_email())
			{
				User user = userExist.getUser();
				if (user.getEmailhash() == emailHash){
					userService.updateUserStatus(user);
					resp.sendRedirect("/login");
				}
				else{
					RequestDispatcher requestDispatcher=req.getRequestDispatcher("/RegisterFailure.jsp");
					requestDispatcher.forward(req, resp);
				}
			}
			else{
				RequestDispatcher requestDispatcher=req.getRequestDispatcher("/RegisterFailure.jsp");
				requestDispatcher.forward(req, resp);
			}
		}
		
	}

}
