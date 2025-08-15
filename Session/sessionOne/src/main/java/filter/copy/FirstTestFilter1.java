package filter.copy;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebFilter("/filterTest11")
public class FirstTestFilter1 implements Filter {
	
	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Filter initialized");
    }	
    
	 @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	            throws IOException, ServletException {
	        
	        HttpServletRequest req = (HttpServletRequest) request;
	        HttpServletResponse res = (HttpServletResponse) response;

	        // Before Servlet
	        System.out.println("Incoming request: " + req.getMethod() + " " + req.getRequestURI());

	        // You can modify the headers or perform verification before allowing the request to complete.
	        // For example, if you want to prevent entry without a token.
	        // Example: check token
	        // if (req.getHeader("Auth-Token") == null) {
	        //     res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	        //     return;
	        // }

	        // The request is allowed to continue to the next servlet or filter.
	        // Pass to next filter or servlet
	        chain.doFilter(request, response);

	        
	        // After Servlet
	        System.out.println("Outgoing response: " + res.getStatus());
	    }
	 
	 @Override
	    public void destroy() {
		 System.out.println("Filter destroyed");
	    }

}
