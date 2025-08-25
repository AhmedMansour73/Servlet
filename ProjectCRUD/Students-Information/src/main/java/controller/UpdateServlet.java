package controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Student;
import linker.DBHelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;


@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Long studentId = Long.parseLong(request.getParameter("id")); 
 
		Student student = DBHelper.getDataById(studentId);
		
		out.print("<html>");
		out.print("<head>");
		out.print("<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">");
		out.print("</head>");
		out.print("<body class='container mt-5'>");

		out.print("<h2 class='mb-4'>Update Student Information</h2>");
		out.print("<form action='UpdateServlet2' method='POST'  onsubmit=\"return validateForm()\"> ");
		out.print("<table class='table table-bordered'>");

		// ID (readonly)
		out.print("<tr><td>ID</td><td>"
		        + "<input type='text' name='id' value='" + student.getId() + "' readonly class='form-control'/>"
		        + "</td></tr>");

		// Name
		out.print("<tr><td>NAME</td><td>"
		        + "<input type='text' class='form-control' id='Name' name='name' "
		        + "value='" + student.getName() + "' required "
		        + "oninput=\"this.setCustomValidity('')\">"
		        + "</td></tr>");

		// SSN
		out.print("<tr><td>SSN</td><td>"
		        + "<input type='text' class='form-control' id='SSN' name='SSN' "
		        + "value='" + student.getSsn() + "' maxlength='14' pattern='[0-9]{14}' required "
		        + "oninvalid=\"this.setCustomValidity('SSN must be 14 digits and not contain letters')\" "
		        + "oninput=\"this.setCustomValidity('')\">"
		        + "</td></tr>");

		// Birth Date
		out.print("<tr><td>BIRTH DATE</td><td>"
		        + "<input type='date' class='form-control' id='BirthDate' name='birthDate' "
		        + "value='" + student.getBirthDate() + "' required min='1900-01-01' max='2015-12-31' "
		        + "oninvalid=\"this.setCustomValidity('BirthDate must be selected')\" "
		        + "oninput=\"this.setCustomValidity('')\">"
		        + "</td></tr>");

		// Country
		out.print("<tr><td>COUNTRY</td><td>"
		        + "<input type='text' class='form-control' id='Country' name='country' "
		        + "value='" + student.getCountry() + "' required>"
		        + "</td></tr>");

		// Age
		out.print("<tr><td>AGE</td><td>"
		        + "<input type='number' class='form-control' id='Age' name='age' "
		        + "value='" + student.getAge() + "' required>"
		        + "</td></tr>");

		// Email
		out.print("<tr><td>EMAIL</td><td>"
		        + "<input type='email' class='form-control' id='Email' name='email' "
		        + "value='" + student.getEmail() + "' "
		        + "oninvalid=\"this.setCustomValidity('Enter valid email')\" "
		        + "oninput=\"this.setCustomValidity('')\">"
		        + "</td></tr>");

		// Password (with toggle eye)
		out.print("<tr><td>PASSWORD</td><td>"
		        + "<div class='input-group'>"
		        + "<input type='password' class='form-control' id='Password' name='password' "
		        + "value='" + student.getPassword() + "' "
		        + "placeholder='Ahmed@123' "
		        + "pattern='^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,32}$' "
		        + "oninvalid=\"this.setCustomValidity('Password must be 8–32 chars and include: uppercase, lowercase, number, special char.')\" "
		        + "oninput=\"this.setCustomValidity('')\">"
		        + "<button type='button' class='btn btn-outline-secondary' onclick='togglePassword()'>👁️</button>"
		        + "</div>"
		        + "</td></tr>");

		// Phone
		out.print("<tr><td>PHONE</td><td>"
		        + "<input type='text' class='form-control' id='PhoneNumber' name='phone' "
		        + "value='" + student.getPhone() + "' maxlength='13' "
		        + "pattern='(01[0125]\\d{8}|\\+201[0125]\\d{8})' required "
		        + "placeholder='01xxxxxxxxx or +20xxxxxxxxxx' "
		        + "oninvalid=\"this.setCustomValidity('Phone must be 11 digits if start with 01, or 13 digits if start with +20')\" "
		        + "oninput=\"this.setCustomValidity('')\">"
		        + "</td></tr>");

		// Submit
		out.print("<tr><td colspan='2' class='text-center'>"
		        + "<input type='submit' value='Update' class='btn btn-primary'/>"
		        + "</td></tr>");

		out.print("<script src=\"js/script.js\"></script>");
//	    out.print("<script src=\"js/bootstrap.bundle.min.js\"></script>");
	    
		out.print("</table>");
		out.print("</form>");
			
		
		
		
	}
	
	
	
	
//protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		
//		response.setContentType("text/html");
//		PrintWriter pWriter = response.getWriter();
//		
//		Long id = Long.parseLong( request.getParameter("id"));
//		String name = request.getParameter("name");
//		String ssn =  request.getParameter("SSN") ;
//		
//		Date birthDate = Date.valueOf( request.getParameter("birthDate") );
//		
//		String country = request.getParameter("country");
//	
//	// get age
//			LocalDate birthLocalDate = birthDate.toLocalDate();
//			Period period = Period.between(birthLocalDate, LocalDate.now());
//			int agePeriod = period.getYears();
//			int age = Integer.parseInt( request.getParameter("age") );
//			if( agePeriod == age) {
//				agePeriod = age;
//			}
//			
//			
//		String email = request.getParameter("email");
//		String password = request.getParameter("password");
//		
//		String phone = request.getParameter("phone") ;
//		
//		Student student = new Student();
//		
//		student.setId( id );
//		student.setName( name );
//		student.setSsn( ssn );
//		student.setBirthDate( birthDate  );
//		student.setCountry( country );
//		student.setAge( agePeriod);
//		student.setEmail(email );
//		student.setPassword(password );
//		student.setPhone( phone );
//		
//		if(DBHelper.connectDB() != null)
//		{
//			int updateNumber = DBHelper.updateDataById(student);
//			if(updateNumber > 0 )
//			{
//				pWriter.println("<h2>Update successfuly :) </h2>");
//				request.getRequestDispatcher("index.html").include(request, response);
//				
////				pWriter.println("<script>");
////				pWriter.println("setTimeout(function(){ window.location.href='" 
////				                + request.getContextPath() + "/index.html'; }, 3000);");
////				pWriter.println("</script>");
//				
//			}else {
//				pWriter.println("<h2>Sorry not Update :( </h2>");	
//			}	
//			
//		}else {
//			pWriter.println("<h2>Sorry not have coonection </h2>");
//			
//		}
//		
//		
//	}

	

}
