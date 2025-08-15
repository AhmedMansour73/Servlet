package dispatcher;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name= "Forward" , urlPatterns="/forward")
public class Forward extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Read parameters
        String n1 = request.getParameter("num1");
        String n2 = request.getParameter("num2");

        int numberOne = Integer.parseInt(n1);
        int numberTwo = Integer.parseInt(n2);

        // Do calculations
        int sum = numberOne + numberTwo;
        int sub = numberOne - numberTwo;
        int mul = numberOne * numberTwo;
        double div = (numberTwo != 0) ? (double) numberOne / numberTwo : Double.NaN;

//        response.setContentType("text/html; charset=UTF-8");
        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("num1 = " + numberOne +", num2 = "+ numberTwo);   // This will appear above result.jsp if Buffer is Full before reach to Forward
        
        // Store result in request scope
        // Put results in request scope
        request.setAttribute("sum", sum);
        request.setAttribute("sub", sub);
        request.setAttribute("mul", mul);
        request.setAttribute("divi", div);

        // Forward to JSP to display result
        // Forward to result.jsp (URL doesn't change)
        RequestDispatcher rd = request.getRequestDispatcher("pages/result.jsp");
        rd.forward(request, response);
        out.println("num1 = " + numberOne +", num2 = "+ numberTwo);   // This will NOT appear 
     
     
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
