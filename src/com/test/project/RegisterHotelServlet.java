package com.test.project;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class RegisterHotelServlet extends CustomServlet {

	@Override
	protected void handleRequest(HttpServletRequest req,
			HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession(false);
		String HotelName = req.getParameter("HotelName");
		String Rooms = req.getParameter("Rooms");
		String Stars = req.getParameter("Stars");
		
		if(session.getAttribute("login") != null && (String) session.getAttribute("login") =="logged_in"){
			if(session.getAttribute("user") == null){
				RequestDispatcher requestDispatcher=req.getRequestDispatcher("/RegisterFailure.jsp");
				requestDispatcher.forward(req, resp);
			}
			else{
				if (HotelName == null || Rooms == null || Stars == null)
				{
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/RegisterHotel.jsp");
					requestDispatcher.forward(req, resp);	
				}
				else
				{
				int rooms = Integer.parseInt(Rooms);
				int stars = Integer.parseInt(Stars);
				Hotel hotel = new Hotel(HotelName, rooms, stars);
				HotelService hotelService = new HotelService();
				if (hotelService.Insert(hotel))
				{
						
					resp.sendRedirect("/rateHotel");
				}
				else
				{
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/RegisterFailure.jsp");
					requestDispatcher.forward(req, resp);	
				}
				
				}
				
			}
		}
		
		
		
		// TODO Auto-generated method stub
		
	}
	
	

}
