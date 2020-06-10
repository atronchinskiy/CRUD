package servlet;

import entity.User;
import service.UserDAOService;
import exception.DBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/user")
public class ServerUser extends HttpServlet {
    UserDAOService userDAOService = UserDAOService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = null;
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        try {
            String login = session.getAttribute("loginSess").toString();
            String password = session.getAttribute("passwordSess").toString();
            user = userDAOService.getUserService(login, password);
            req.setAttribute("user", user);
            req.getRequestDispatcher("/user.jsp").forward(req, resp);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
