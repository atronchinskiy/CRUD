package filter;

import entity.User;
import service.UserDAOService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import exception.DBException;

@WebFilter(urlPatterns = "/admin")
public class FilterAdmin implements Filter {

    UserDAOService userDAOService = UserDAOService.getInstance();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String login = ((HttpServletRequest) request).getSession().getAttribute("loginSess").toString();
        String password = ((HttpServletRequest) request).getSession().getAttribute("passwordSess").toString();
        ((HttpServletRequest) request).setCharacterEncoding("UTF-8");
        ((HttpServletResponse) response).setCharacterEncoding("UTF-8");

        User user = null;

        try {
            user = userDAOService.getUserService(login, password);
            String role = user.getRole();
            if (user.getRole().equals("admin")) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse) response).sendRedirect("/login");
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
