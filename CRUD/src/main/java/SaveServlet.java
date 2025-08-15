

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Student;
import utils.DBHelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SaveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pWriter = response.getWriter();
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String country = request.getParameter("country");
		
		Student student = new Student();
		student.setName(name);
		student.setEmail(email);
		student.setPassword(password);
		student.setCountry(country);
		
		if(DBHelper.connectDB() != null)
		{
			int insertNumber = DBHelper.saveData(student);
			if(insertNumber > 0 )
			{
				pWriter.println("<h2>Saved successfuly :)</h2>");
				request.getRequestDispatcher("index.html").include(request, response);
			}else {
				pWriter.println("<h2>Sorry not saved :(</h2>");	
			}
			
		}else {
			pWriter.println("<h2>Sorry not have coonection</h2>");
			
		}
		
				
				
		
	}

}
