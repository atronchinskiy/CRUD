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

@WebServlet(urlPatterns = "/admin/edit")

public class ServerEdit extends HttpServlet {
    UserDAOService userDAOService = UserDAOService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setCharacterEncoding("UTF-8");
            req.setCharacterEncoding("UTF-8");
            String id = req.getParameter("id");
            String name = req.getParameter("name");
            String lastName = req.getParameter("lastName");
            String age = req.getParameter("age");
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            String role = req.getParameter("role");
            User user = new User(Long.parseLong(id), name, lastName, Long.parseLong(age), login, password, role);
            req.setAttribute("user", user);
            req.getRequestDispatcher("/edit.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setCharacterEncoding("UTF-8");
            req.setCharacterEncoding("UTF-8");
            String id = req.getParameter("id");
            String name = req.getParameter("name");
            String lastName = req.getParameter("lastName");
            String age = req.getParameter("age");
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            String role = req.getParameter("role");
            User user = new User(Long.parseLong(id), name, lastName, Long.parseLong(age), login, password, role);
            userDAOService.editUserService(user);
            req.setAttribute("user", user);
//            resp.sendRedirect("/CrudMaven/admin");
            resp.sendRedirect("/CrudProj/admin");
        } catch (DBException e) {
            e.printStackTrace();
        }

    }
}
