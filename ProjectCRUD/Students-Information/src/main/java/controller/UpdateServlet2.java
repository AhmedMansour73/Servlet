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
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

@WebServlet("/UpdateServlet2")
public class UpdateServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html");
		PrintWriter pWriter = response.getWriter();
		
		Long id = Long.parseLong( request.getParameter("id"));
		String name = request.getParameter("name");
		String ssn =  request.getParameter("SSN") ;
		
		Date birthDate = Date.valueOf( request.getParameter("birthDate") );
		
		String country = request.getParameter("country");
		
		// get age
		LocalDate birthLocalDate = birthDate.toLocalDate();
		Period period = Period.between(birthLocalDate, LocalDate.now());
		int agePeriod = period.getYears();
		int age = Integer.parseInt( request.getParameter("age") );
		if( agePeriod == age) {
			agePeriod = age;
		}
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		String phone = request.getParameter("phone") ;
		
		Student student = new Student();
		
		student.setId( id );
		student.setName( name );
		student.setSsn( ssn );
		student.setBirthDate( birthDate  );
		student.setCountry( country );
		student.setAge( agePeriod );
		student.setEmail(email );
		student.setPassword(password );
		student.setPhone( phone );
		
		if(DBHelper.connectDB() != null)
		{
			
			int updateNumber = DBHelper.updateDataById(student);
			if(updateNumber > 0 )
			{
				pWriter.println("<h2>Update successfuly :) </h2>");
//				request.getRequestDispatcher("UpdateServlet").include(request, response);
				
//				pWriter.println("<script>");
//				pWriter.println("setTimeout(function(){ window.location.href='" 
//				                + request.getContextPath() + "/index.html'; }, 3000);");
//				pWriter.println("</script>");
				
			}else {
				if(DBHelper.chackPhoneUnique(phone) > 0 || DBHelper.chackEmailUnique(email) > 0 || DBHelper.chackSSNUnique(ssn) > 0) {
					pWriter.println("<h2>Can't update Student because you try add ( SSN or Email or Phone Number ) already exist</h2>");
					pWriter.println("<h3>Please chack them</h3>");
				}else {
				pWriter.println("<h2>Sorry not Update :( </h2>"); }	
			}	
			
		}else {
			pWriter.println("<h2>Sorry not have coonection </h2>");
			
		}
		
		
	}

}
