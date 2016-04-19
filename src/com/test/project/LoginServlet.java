package com.test.project;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class LoginServlet extends CustomServlet {
	@Override
	protected void handleRequest(HttpServletRequest req,
			HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession(true);
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		UserService userService = new UserService();
		Integer counter = new Integer(0);
		if ((email ==null || password ==null) && (session.getAttribute("login") == null)){
			
			RequestDispatcher requestDispatcher=req.getRequestDispatcher("/Login.jsp");
			requestDispatcher.forward(req, resp);
			

		}

		else if ((String) session.getAttribute("login") == "logged_in")
		{
			resp.sendRedirect("/home");
		}
		
		
		else if(((String) session.getAttribute("login"))=="logged_out")
		{
			
			resp.sendRedirect("/home");
		}
		else{

				UserExist userExist = userService.Check_email(email); 
				if (userExist.isCheck_email()){
					User user = userExist.getUser();
					if (userService.checkPassword(password, user.getPasswordHash() )){
						if(user.isActivated())
						{
						String logged_in = "logged_in";
						session.setAttribute("user", user);
						session.setAttribute("login", logged_in);
						resp.sendRedirect("/home");
						}
						else{
							RequestDispatcher requestDispatcher=req.getRequestDispatcher("/RegisterFailure.jsp");
							requestDispatcher.forward(req, resp);
						}
					}
					else{
						if(session.getAttribute("Count")==null){
							session.setAttribute("Count", 0);
						}
						counter = (Integer) session.getAttribute("Count");
						counter = counter+1;
						session.setAttribute("Count", counter);
						System.out.println(counter);
						if(counter==5){
							System.out.println(counter);
							session.setAttribute("login", "logged_out");
						}
						resp.sendRedirect("/login");

					}

				}
				else{
					if(session.getAttribute("Count")==null){
						session.setAttribute("Count", 0);
					}
					counter = (Integer) session.getAttribute("Count");
					counter = counter+1;
					session.setAttribute("Count", counter);
					System.out.println(counter);
					if(counter==5){
						System.out.println(counter);
						
						session.setAttribute("login", "logged_out");
					}
					resp.sendRedirect("/login");
				}
				
			

			}
			
			}


		}

