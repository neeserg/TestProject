package com.test.project;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class RegisterHotelServlet extends CustomServlet {

	@Override
	protected void handleRequest(HttpServletRequest req,
			HttpServletResponse resp) throws IOException, ServletException {
		
		String HotelName = req.getParameter("HotelName");
		String Rooms = req.getParameter("Rooms");
		String Stars = req.getParameter("Stars");
		
		
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
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/RegisterSuccess.jsp");
			requestDispatcher.forward(req, resp);
		}
		else
		{
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/RegisterFailure.jsp");
			requestDispatcher.forward(req, resp);	
		}
		
		}
		// TODO Auto-generated method stub
		
	}
	
	

}
