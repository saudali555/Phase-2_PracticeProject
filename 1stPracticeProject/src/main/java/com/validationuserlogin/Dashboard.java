package com.validationuserlogin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/dash")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session= request.getSession();
		String name=(String)session.getAttribute("user");
		
		
		if(name!=null)
		{
			out.print("User Found");
			out.print("<br>Welcome "+name);
			
			out.print("<br> <a href=\"logout\"><button type=\"button\">Logout</button></a>");
		}
		else
		{
			out.print("No user found");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}