package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/FTest")
public class FirstTestFilter implements Filter {
	
	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Filter initialized");
    }	
    
	 @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	            throws IOException, ServletException {
	        
		 HttpServletRequest req = (HttpServletRequest) request;
	        HttpServletResponse res = (HttpServletResponse) response;

	       
	        System.out.println("Before Servlet: " + req.getRequestURI());

	        chain.doFilter(request, response);

	        System.out.println("After Servlet: " + res.getStatus());
	    }
	 
	 @Override
	    public void destroy() {
		 System.out.println("Filter destroyed");
	    }

}
