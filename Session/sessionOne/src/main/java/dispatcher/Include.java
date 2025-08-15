package dispatcher;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name= "Include" , urlPatterns="/include")
public class Include extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
        out.println("<html><body>");

        // Include header content
        RequestDispatcher rd = request.getRequestDispatcher("pages/header.jsp");
        rd.include(request, response);

        out.println("<h2>Main Content Here...</h2>");

        // Include footer content
        rd = request.getRequestDispatcher("pages/footer.jsp");
        rd.include(request, response);

        out.println("</body></html>");
     
     
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
