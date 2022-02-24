package com.productdetails;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.SendResult;

@WebServlet("/list")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Properties props = new Properties();
		props.load(getServletContext().getResourceAsStream("/WEB-INF/application.properties"));
		try {
			DatabaseConfig config = new DatabaseConfig(props.getProperty("driver"), props.getProperty("url"),
					props.getProperty("username"), props.getProperty("password"));

			Connection conn = config.getConnection();

			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet result = stmt.executeQuery("select id,name from eproduct");
			out.print("<h1>Product List</h1><hr>");
			while (result.next()) {
				int id = result.getInt(1);
				String name = result.getString(2);
				out.print(id + " " + "<a href='details?id=" + id + "'>" + name + "</a><br>");
			}
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}