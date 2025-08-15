package dispatcher;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name= "Redirect" , urlPatterns="/redirect")
public class Redirect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String user = request.getParameter("username");
        String pass = request.getParameter("password");

        if ("admin".equals(user) && "123".equals(pass)) {
            // Redirect to dashboard
            response.sendRedirect(request.getContextPath() + "/include");
        } else {
            // Redirect to error page
            response.sendRedirect("pages/error.jsp");
        }
     
     
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
