package cookieAndsession;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/cookie")
public class Cookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		jakarta.servlet.http.Cookie cookie = new jakarta.servlet.http.Cookie("username", "Ahmed");
		
        cookie.setMaxAge(60 * 60); 
        response.addCookie(cookie);

      
        Cookie[] cookies =  request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                response.getWriter().println(c.getName() + " = " + c.getValue() + "<br>");
            }
        }
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
