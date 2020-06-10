package dao;

import entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    public List<?> getAllUsers() throws SQLException;

    public User getUser(String login, String password) throws SQLException;

    public void addUser(User user) throws SQLException;

    public void deleteUser(Long id) throws SQLException;

    public void editUser(User user) throws SQLException;
}
