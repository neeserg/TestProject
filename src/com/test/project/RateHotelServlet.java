package com.test.project;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class RateHotelServlet extends CustomServlet {

	@Override
	protected void handleRequest(HttpServletRequest req,
			HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession(false);
		
		if(session.getAttribute("login") != null && (String) session.getAttribute("login") =="logged_in"){
			if(session.getAttribute("user") == null){
				RequestDispatcher requestDispatcher=req.getRequestDispatcher("/RegisterFailure.jsp");
				requestDispatcher.forward(req, resp);
			}
			else{
				String stars = req.getParameter("stars");
				if (stars == null){
				HotelService hotelService=new HotelService();
				List<Hotel> hotels=hotelService.listHotels();
				req.setAttribute("hotels", hotels);
				RequestDispatcher requestDispatcher=req.getRequestDispatcher("/RateHotel.jsp");
				requestDispatcher.forward(req, resp);
				}
				else{
					UserService userService = new UserService();
					User user = (User) session.getAttribute("user");
					Long hotelId =  Long.valueOf(req.getParameter("hotelId"));
					int numStars = Integer.parseInt(stars);
					if(userService.updateUser(user, hotelId, numStars)){
						resp.sendRedirect("/home");
					}
				}
	
			}
		}
		else{
			resp.sendRedirect("/home");
		}
		
		
			
	}
	

}
