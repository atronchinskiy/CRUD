package servlet;

import entity.User;
import service.UserDAOService;
import exception.DBException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin")
public class ServerAdmin extends HttpServlet {

    UserDAOService userDAOService = UserDAOService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = null;
        try {
            resp.setCharacterEncoding("UTF-8");
            req.setCharacterEncoding("UTF-8");
            users = userDAOService.getAllUsersService();
            req.setAttribute("users", users);
            req.getRequestDispatcher("/admin.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setCharacterEncoding("UTF-8");
            req.setCharacterEncoding("UTF-8");
            String name = req.getParameter("name");
            String lastName = req.getParameter("lastName");
            String age = req.getParameter("age");
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            String role = req.getParameter("role");
            User user = new User(name, lastName, Long.parseLong(age), login, password, role);
            userDAOService.addUserService(user);
            resp.sendRedirect(req.getContextPath() + "/admin");
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}