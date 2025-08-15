package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/secure/*") // This filter applies to all URLs starting with /secure
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false); // Don't create new session

        boolean loggedIn = (session != null && session.getAttribute("user") != null);

        if (loggedIn) {
            System.out.println("User is logged in. Continuing...");
            chain.doFilter(request, response); // Continue to servlet
        } else {
            System.out.println("User is NOT logged in. Redirecting to login page...");
            res.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }
}

