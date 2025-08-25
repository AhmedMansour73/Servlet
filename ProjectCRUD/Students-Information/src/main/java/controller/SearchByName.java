package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import linker.DBHelper;
import model.Student;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class SearchByName
 */
@WebServlet("/SearchByName")
public class SearchByName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchByName() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
			
		
		// استدعاء DAO أو كود JDBC
        List<Student> list = new ArrayList<>();

        String search = request.getParameter("search");
        
        list = DBHelper.getDataByname(search);
       

        // HTML
        out.print("<head>");
        out.print("<link rel='stylesheet' href='css/bootstrap.min.css'>");
        out.print("</head>");
        out.print("<body class='container mt-4'>");
        
        out.print("<form class='mb-3' method='get' action='SearchByName'>");
        out.print("   <div class='input-group'>");
        out.print("       <input type='text' name='search' class='form-control' placeholder='Search by name'>");
        out.print("       <button type='submit' class='btn btn-primary'>Search</button>");
        out.print("   </div>");
        out.print("</form>");
        

        out.print("<table class='table table-striped table-bordered'>");
        out.print("<thead class='table-dark'>");
        out.print("<tr>"
                + "<th>ID</th>"
                + "<th>Name</th>"
                + "<th>SSN</th>"
                + "<th>Birth Date</th>"
                + "<th>Email</th>"
                + "<th>Password</th>"
                + "<th>Country</th>"
                + "<th>Age</th>"
                + "<th>Phone</th>"
                + "<th>Edit</th>"
                + "<th>Delete</th>"
                + "</tr>");
        out.print("</thead>");
        out.print("<tbody>");

        for (Student stud : list) {
            out.print("<tr>");
            out.print("<td>" + stud.getId() + "</td>");
            out.print("<td>" + stud.getName() + "</td>");
            out.print("<td>" + stud.getSsn() + "</td>");
            out.print("<td>" + stud.getBirthDate() + "</td>");
            out.print("<td>" + stud.getEmail() + "</td>");
            out.print("<td>" + stud.getPassword() + "</td>");
            out.print("<td>" + stud.getCountry() + "</td>");
            out.print("<td>" + stud.getAge() + "</td>");
            out.print("<td>" + stud.getPhone() + "</td>");
            out.print("<td><a class='btn btn-warning btn-sm' href='UpdateServlet?id=" + stud.getId() + "'>Edit</a></td>");
            out.print("<td><a class='btn btn-danger btn-sm' href='DeleteServlet?id=" + stud.getId() + "'>Delete</a></td>");
            out.print("</tr>");
        }

        out.print("</tbody>");
        out.print("</table>");

        out.print("</body>");
		
	}

}
