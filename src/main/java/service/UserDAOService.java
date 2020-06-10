package service;

import dao.UserDAO;
import dao.UserDaoFactory;
import entity.User;
import exception.*;
import java.sql.SQLException;
import java.util.List;

public class UserDAOService implements ServiceDAO {
    private static UserDAOService userDAOService;
    private static UserDAO userDAO;

    protected UserDAOService() {

    }

    public static  UserDAOService getInstance() {
        if (userDAOService == null) {
            try {
                userDAO = UserDaoFactory.getDao();
            } catch (UtilExc utilExc) {
                utilExc.getMessage();
                utilExc.printStackTrace();
            }
            userDAOService = new UserDAOService();
        }
        return userDAOService;
    }

    public List<User> getAllUsersService() throws DBException {
        List<User> list;
        try {
            list = (List<User>) userDAO.getAllUsers();
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return list;
    }

    public User getUserService(String login, String password) throws DBException {
        User user;
        try {
            user = userDAO.getUser(login, password);
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return user;
    }

    public boolean addUserService(User user) throws DBException {
        try {
            userDAO.addUser(user);
            return true;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public boolean deleteUserService(Long id) throws DBException {
        try {
            userDAO.deleteUser(id);
            return true;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public boolean editUserService(User user) throws DBException {
        try {
            userDAO.editUser(user);
            return true;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

}
