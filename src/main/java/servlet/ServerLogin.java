package servlet;

import entity.User;
import service.UserDAOService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import exception.DBException;

@WebServlet(urlPatterns = "/login")
public class ServerLogin extends HttpServlet {

    UserDAOService userDAOService = UserDAOService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setCharacterEncoding("UTF-8");
            req.setCharacterEncoding("UTF-8");

            String loginSess = null;
            String passwordSess = null;
            HttpSession session = req.getSession();

            loginSess = session.getAttribute("loginSess").toString();
            passwordSess = session.getAttribute("passwordSess").toString();
            User user = userDAOService.getUserService(loginSess, passwordSess);


            if (user != null && user.getRole().equals("admin")) {
                resp.sendRedirect(req.getContextPath() + "/admin");
            } else if (user != null && user.getRole().equals("user")) {
             resp.sendRedirect(req.getContextPath() + "/user");
            } else {
                resp.sendRedirect(req.getContextPath() + "/login");
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
