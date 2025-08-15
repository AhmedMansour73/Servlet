package com.ahmed;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class firstpage
 */
@WebServlet("/firstpage")
public class firstpage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public firstpage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String userName = request.getParameter("username");
//		
//		PrintWriter printwriter = response.getWriter();
//		printwriter.println("username = " + userName);
	}

	
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		
		PrintWriter printwriter = response.getWriter();
		
		if(passWord.equals("123"))
		{
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("/progiles.html");
			reqDispatcher.forward(request, response);
		}else {
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("/index.html");
			printwriter.println("check username/password");
			reqDispatcher.include(request, response);
		}
		
	}

}
