

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

@WebServlet("/UpdateSrvlet2")
public class UpdateSrvlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateSrvlet2() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pWriter = response.getWriter();
		
		String studentId = request.getParameter("id");
		int id = Integer.parseInt(studentId);
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String country = request.getParameter("country");
		
		Student student =new Student();
		student.setId(id);
		student.setName(name);
		student.setEmail(email);
		student.setPassword(password);
		student.setCountry(country);
		

		int updateNumber =DBHelper.updateData(student);
		if(updateNumber > 0 )
		{
			response.sendRedirect("ViewSevlet");
		}else {
			pWriter.println("<h2>Sorry not updeted :( </h2>");
			
		}
		
	
		
	}

}
