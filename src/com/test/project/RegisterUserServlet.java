package com.test.project;

import java.io.IOException;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







@SuppressWarnings("serial")
public class RegisterUserServlet extends  CustomServlet {



	@Override
	protected void handleRequest(HttpServletRequest req,
			HttpServletResponse resp) throws IOException, ServletException {
		
		

		String fName = req.getParameter("fName");
		String lName = req.getParameter("lName");
		String email = req.getParameter("email");
		String ageString =req.getParameter("age");
		String hotelIdString = req.getParameter("hotelId");
		String numStarsString = req.getParameter("stars");

		if (fName == null || lName == null || email == null || ageString == null || hotelIdString ==null || numStarsString == null )
		{
			HotelService hotelService=new HotelService();
			List<Hotel> hotels=hotelService.listHotels();
			req.setAttribute("hotels", hotels);
			RequestDispatcher requestDispatcher=req.getRequestDispatcher("/Register.jsp");
			requestDispatcher.forward(req, resp);
		}
		else 
		{
			int age=Integer.parseInt(ageString);
			Long hotelId = Long.valueOf(hotelIdString);
			int numStars = Integer.parseInt(numStarsString);
			
			User user=new User(age, fName, lName, email);
			user.addHotelId(hotelId);
			user.addStarRatings(numStars);
			UserService userService=new UserService();
			if(userService.insertUser(user)){
		   
		    
			RequestDispatcher requestDispatcher=req.getRequestDispatcher("/RateHotel.jsp");
			requestDispatcher.forward(req, resp);
			
			}else{
				RequestDispatcher requestDispatcher=req.getRequestDispatcher("/RegisterFailure.jsp");
				requestDispatcher.forward(req, resp);
			}
		}


	}






}
