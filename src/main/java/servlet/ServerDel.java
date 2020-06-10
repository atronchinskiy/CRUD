package servlet;

import service.UserDAOService;
import exception.DBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete")
public class ServerDel extends HttpServlet {

    UserDAOService userDAOService = UserDAOService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        try {
            userDAOService.deleteUserService(id);
            resp.sendRedirect(req.getContextPath() + "/admin") ;
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
