package filter;
import entity.User;
import service.UserDAOService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import exception.DBException;

@WebFilter(value = "/user")
public class FilterUser implements Filter {
    UserDAOService userDAOService = UserDAOService.getInstance();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String login =  ((HttpServletRequest) request).getSession().getAttribute("loginSess").toString();
        String password = ((HttpServletRequest) request).getSession().getAttribute("passwordSess").toString();
        ((HttpServletRequest) request).setCharacterEncoding("UTF-8");
        ((HttpServletResponse) response).setCharacterEncoding("UTF-8");

        User user = null;

        try {
            user = userDAOService.getUserService(login, password);
            String role = user.getRole();
            if (user.getRole().equals("user")) {
                chain.doFilter(request, response);
            } else {
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}

