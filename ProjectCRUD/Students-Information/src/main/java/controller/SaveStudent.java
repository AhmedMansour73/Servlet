package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Student;
import linker.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;


@WebServlet("/SaveStudent")
public class SaveStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveStudent() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html");
		PrintWriter pWriter = response.getWriter();
		
		String name = request.getParameter("name");
		String ssn =  request.getParameter("SSN") ;
		
		Date birthDate = Date.valueOf( request.getParameter("birthDate") );
		
		String country = request.getParameter("country");
		
		// get age
		LocalDate birthLocalDate = birthDate.toLocalDate();
		// We calculate age based on years and months and day.
		Period period = Period.between(birthLocalDate, LocalDate.now());
		// We calculate age based on the year only.
//		int ageByYear = LocalDate.now().getYear() - birthLocalDate.getYear();
		
		int agePeriod = period.getYears();
		int age = Integer.parseInt( request.getParameter("age") );
		if( agePeriod == age) {
			agePeriod = age;
		}
		
	
	

		
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		String phone = request.getParameter("phone") ;
		
		Student student = new Student();
		
        
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
			
			
			if(DBHelper.chackSSNUnique(ssn) > 0)
			{
				pWriter.println("<h2>Can't add Student because you try add SSN already exist</h2>");
			}else if(DBHelper.chackEmailUnique(email) > 0) {
				pWriter.println("<h2>Can't add Student because you try add Email already exist</h2>");
			}
			else if(DBHelper.chackPhoneUnique(phone) > 0) {
				pWriter.println("<h2>Can't add Student because you try add Phone Number already exist</h2>");
			}else {
					int insertNumber = DBHelper.saveData(student);
					if(insertNumber > 0 )
					{
						pWriter.println("<h2>Saved successfuly :)</h2>");
		//				request.getRequestDispatcher("index.html").include(request, response);
					}else {
						pWriter.println("<h2>Sorry not saved :(</h2>");	 }
				}
			}
		else {
			pWriter.println("<h2>Sorry not have coonection</h2>");
			
		}
		
	}

}
