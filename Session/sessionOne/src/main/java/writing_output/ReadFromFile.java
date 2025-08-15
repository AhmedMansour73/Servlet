package writing_output;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/readFile")
public class ReadFromFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        // must be have file output.txt on F
        String filePath = "F:/output.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            out.println("<html><body>");
            out.println("<h1>File Content:</h1><pre>");

            String line;
            while ((line = br.readLine()) != null) {
                out.println(line);
            }

            out.println("</pre></body></html>");
        } catch (FileNotFoundException e) {
            out.println("<p style='color:red;'>File not found: " + filePath + "</p>");
        } catch (IOException e) {
            out.println("<p style='color:red;'>Error reading file: " + e.getMessage() + "</p>");
        }
    
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
	}

}
