

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


@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pWriter = response.getWriter();
		pWriter.println("<h2>Update Stuent Info </h2>");
		
		String studentId = request.getParameter("id");
		int id = Integer.parseInt(studentId);

		Student student = DBHelper.getDataById(id);
		pWriter.print("<head>");
		pWriter.print("<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">");
		pWriter.print("</head>");
		pWriter.print("<body>");
		pWriter.print("<form action=\"UpdateServlet2\" action=\"POST\" >");
		pWriter.print("<table>");
		pWriter.print("<tr><td></td><td>"
				+ "<input name='id' value='"+student.getId()+"'/>"
						+ "</td></tr>");
		pWriter.print("<tr><td>NAME</td><td>"
				+ "<input type='text' name='name' value='"+student.getName()+"'/>"
				+ "</td></tr>");
		
		pWriter.print("<tr><td>EMAIL</td><td>"
				+ "<input type='email' name='email' value='"+student.getEmail()+"'/>"
				+ "</td></tr>");
		pWriter.print("<tr><td>PASSWORD</td><td>"
				+ "<input type='password' name='password' value='"+student.getPassword()+"'/>"
				+ "</td></tr>");
		pWriter.print("<tr><td>COUNTRY</td><td>"
				+ "<input type='text' name='country' value='"+student.getCountry()+"'/>"
				+ "</td></tr>");
		pWriter.print("");
		pWriter.print("<tr><td colspan ='2'><input type='submit'/></td></tr>");
		pWriter.print("</table>");
		pWriter.print("</form>");
		pWriter.print("</body>");
			
	
		
		
		
	}

	

}
