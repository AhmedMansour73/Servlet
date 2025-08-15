package writing_output;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileWriter;
import java.io.IOException;

@WebServlet("/writeFile")
public class WriteOnFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("txt");
    
	    FileWriter fw = new FileWriter("F:/output.txt", true);
	    fw.write(data + "\n");
	    fw.close();
	
	    response.getWriter().println("Data saved!");
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("text");
        
        FileWriter fw = new FileWriter("F:/output.txt", true);
        fw.write(data + "\n");
        fw.close();

        response.getWriter().println("Data saved!");
		
	}

}
