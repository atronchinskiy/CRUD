package filter;

import entity.User;
import service.UserDAOService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import exception.DBException;

@WebFilter(urlPatterns = "/*")
public class FilterRoot implements Filter {

    UserDAOService userDAOService = UserDAOService.getInstance();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ((HttpServletRequest) request).setCharacterEncoding("UTF-8");
        ((HttpServletResponse) response).setCharacterEncoding("UTF-8");
        User user = null;
        HttpSession session = ((HttpServletRequest) request).getSession();
        String loginSess = null;
        String passwordSess = null;

        if (session.getAttribute("loginSess") != null && session.getAttribute("passwordSess") != null) {
            loginSess = session.getAttribute("loginSess").toString();
            passwordSess = session.getAttribute("passwordSess").toString();
        } else {
            loginSess = ((HttpServletRequest) request).getParameter("login");
            passwordSess = ((HttpServletRequest) request).getParameter("password");
            session.setAttribute("loginSess", loginSess);
            session.setAttribute("passwordSess", passwordSess);
        }

        try {
            user = userDAOService.getUserService(loginSess, passwordSess);
        } catch (DBException e) {
            e.printStackTrace();
        }

        if (user != null && user.getRole().equals("admin")) {
            chain.doFilter(request, response);
        } else if (user != null && user.getRole().equals("user")) {
//            ((HttpServletResponse) response).sendRedirect("/user");
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}

