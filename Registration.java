package com.app;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Servlet implementation class Registration
 */
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<h1>hello</h1>");
		String r = request.getParameter("r");
		String e = request.getParameter("e");
		String p = request.getParameter("p");
		
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection  conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping", "root","root");
			 
				String query = "insert into reg values('"+r+"','"+e+"','"+p+"')";
		 
		     Statement st = conn.createStatement();
		   int status =  st.executeUpdate(query);
			 if(status != 0)
			 {
				out.print("<h1> register successfull</h1>"); 
				response.sendRedirect("index.html");
			 }
			 else {
				 out.print("<h1> register Unsuccessfull</h1>"); 
			 }
			 st.close();
			 conn.close();
		}
		catch(Exception n)
		{
			out.print("<h1> register Unsuccessfull</h1>");
		}
	}

}
