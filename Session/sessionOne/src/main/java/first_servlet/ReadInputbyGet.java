package first_servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ReadInputbyGet" , urlPatterns = "/calc")
public class ReadInputbyGet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html; charset=UTF-8");
//		like 
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String numberOne = request.getParameter("numberOne");
		String numberTwo = request.getParameter("numberTwo");

				if (numberOne == null || numberOne.isEmpty() || numberTwo == null || numberTwo.isEmpty()) {
				    response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
				    out.println("Missing parameter(s)!");
				} else {
				    try {
				        int num1 = Integer.parseInt(numberOne);
				        int num2 = Integer.parseInt(numberTwo);
				
				        out.println("sum = " + (num1 + num2) + "<br>");
				        out.println("subtraction = " + (num1 - num2) + "<br>");
				        out.println("multiplication = " + (num1 * num2) + "<br>");
				
				        try {
				            out.println("division = " + (num1 / num2) + "<br>");
				        } catch (ArithmeticException e) {
				            out.println("Division error: " + e.getMessage() + "<br>");
				        }
				    } catch (NumberFormatException e) {
				        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				        out.println("Invalid number format!");
				    }
				}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
