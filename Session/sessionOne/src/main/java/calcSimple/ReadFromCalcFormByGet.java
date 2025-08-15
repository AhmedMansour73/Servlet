package calcSimple;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "ReadFromCalcFormByGet" , urlPatterns = "/calcForm")
public class ReadFromCalcFormByGet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            double numberOne = Double.parseDouble(request.getParameter("number1"));
            double numberTwo = Double.parseDouble(request.getParameter("number2"));
            String operation = request.getParameter("operation");

            switch (operation) {
                case "add":
                    out.println("Result = " + (numberOne + numberTwo));
                    break;
                case "sub":
                    out.println("Result = " + (numberOne - numberTwo));
                    break;
                case "mul":
                    out.println("Result = " + (numberOne * numberTwo));
                    break;
                case "div":
                    if (numberTwo == 0) {
                        out.println("Error: Division by zero");
                    } else {
                        out.println("Result = " + (numberOne / numberTwo));
                    }
                    break;
                default:
                    out.println("Unknown operation");
            }
        } catch (NumberFormatException e) {
            out.println("Invalid number format");
        }
    }
	
	
}
