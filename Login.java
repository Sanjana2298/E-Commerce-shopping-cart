package com.app;

import jakarta.servlet.ServletException;
import java.sql.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private PreparedStatement st;
	ResultSet rs;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userId= request.getParameter("userId");
		String password= request.getParameter("password");
		out.print("<h1>good</h2>");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping", "root","root");
			String query = "SELECT userName, password FROM reg WHERE userName = ? AND password = ? ";
			 st = conn.prepareStatement(query);
			 st.setString(1, userId);
			 st.setString(2, password);
			       rs = st.executeQuery();
			  
			  if(rs.next())
			  {
				  out.print("<h1>" +userId+": welcome to home page</h1><br>");
				out.print("<h1>successfully login</h1>"); 
				response.sendRedirect("nextpage.html");
				
			  }
			  else {
				  out.print("<h1>please check you details</h1>"); 
				  response.sendRedirect("index.html");
			  }
			  rs.close();
			  st.close();
			  conn.close();
			  
		} catch (ClassNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.print("<h1>good</h2>");
	}

}
