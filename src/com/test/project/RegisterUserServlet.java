package com.test.project;

import java.io.IOException;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;







@SuppressWarnings("serial")
public class RegisterUserServlet extends  CustomServlet {



	@Override
	protected void handleRequest(HttpServletRequest req,
			HttpServletResponse resp) throws IOException, ServletException {
		
		HttpSession session = req.getSession(true);
		if(session.getAttribute("login") == null || session.getAttribute("login") =="logged_out"){
			
			String fName = req.getParameter("fName");
			String lName = req.getParameter("lName");
			String email = req.getParameter("email");
			String ageString =req.getParameter("age");
			String password = req.getParameter("password");
			String errorMessage = "";

			if (fName == null || lName == null || email == null || ageString == null || password == null)
			{
				req.setAttribute("Error", errorMessage);
				RequestDispatcher requestDispatcher=req.getRequestDispatcher("/Register.jsp");
				requestDispatcher.forward(req, resp);
			}
			else 
			{
				UserService userService=new UserService();
				if (!userService.Check_email(email).isCheck_email()){
					
					int age=Integer.parseInt(ageString);
					
					List<String> password_hash = userService.getHashed_Password(password);
					String emailHash = userService.get_Hash();
					User user=new User(age, fName, lName, email,password_hash,emailHash);
					
					if(userService.insertUser(user)){
						userService.sendEmail(user);
						resp.sendRedirect("/verifyEmail");
					
					}
					else{
						RequestDispatcher requestDispatcher=req.getRequestDispatcher("/RegisterFailure.jsp");
						requestDispatcher.forward(req, resp);
					}
					
				}
				else {
					errorMessage = "Email already taken.";
					req.setAttribute("Error", errorMessage);
					RequestDispatcher requestDispatcher=req.getRequestDispatcher("/Register.jsp");
					requestDispatcher.forward(req, resp);
					
				}
			}
			
		}
		
		else if(session.getAttribute("login") != null && (String) session.getAttribute("login") =="logged_in"){
			if(session.getAttribute("user") != null){
				RequestDispatcher requestDispatcher=req.getRequestDispatcher("/Welcome.jsp");
				requestDispatcher.forward(req, resp);
			}

		


	}






}
}
