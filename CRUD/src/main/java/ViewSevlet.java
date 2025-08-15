

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
import java.util.List;

/**
 * Servlet implementation class ViewSevlet
 */
@WebServlet("/ViewSevlet")
public class ViewSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public ViewSevlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");
	PrintWriter pWriter = response.getWriter();		
	
	List<Student> list= DBHelper.getAllData();
	
	pWriter.print("<head>");
	pWriter.print("<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">");
	pWriter.print("</head>");
	
	pWriter.print("<body>");
	pWriter.print("<a class=\"btn btn-secondary w-100\" href=\"index.html\">Add Student</a>");
	pWriter.print("<h1>Studnt table </h1>");
	pWriter.print("<table border='1' width='100%'>");
	pWriter.print("<tr><th>Id</th><th>Name</th>"
			+ "<th>Email</th><th>Password</th><th>country</th>"
			+ "<th>Edit</th><th>Delete</th>");
	
	for(Student stud:list)
	{
		pWriter.print("<tr><td>"+stud.getId()+"</td> <td>"+stud.getName()+""
				+ "<td>"+stud.getEmail()+"</td> <td>"+stud.getPassword()+" "
				+ "<td>"+stud.getCountry()+"</td> <td><a href='UpdateServlet?id="+stud.getId()+"'>edit</a></td>"
				+ "<td><a href='DeleteServlet?id="+stud.getId()+"'>delete</a></td>");
	}
	
	
	pWriter.print("</table>");
	pWriter.print("</body>");
	
	
	
	}

	
}
