package writing_output;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/downlodeFile")
public class DownFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Name of the file to be downloaded
        String fileName = "example.pdf";

        // File path on the server
        String filePath = "F:/output.txt";

        // Specify the type of content
        response.setContentType("application/pdf");

        // The header that forces the browser to load
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        // Read the file and send it to the client
        try (FileInputStream fileIn = new FileInputStream(filePath);
             OutputStream out = response.getOutputStream()) {

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fileIn.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
