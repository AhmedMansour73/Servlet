package controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import linker.DBHelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public DeleteServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pWriter = response.getWriter();
		
		Long id = Long.parseLong( request.getParameter("id"));
	
		if(DBHelper.connectDB() != null)
		{
			int deleteNumber = DBHelper.deleteDataById( id);;
			if(deleteNumber > 0 )
			{
				pWriter.println("<h2>Delete successfuly :) </h2>");	
				response.sendRedirect("ViewMembersSrevlet");
				
			}else {
				pWriter.println("<h2>Sorry not Deleted :( </h2>");	
			}	
			
		}else {
			pWriter.println("<h2>Sorry not have coonection </h2>");
			
		}
		
		
		
		
		
		
	}


}
