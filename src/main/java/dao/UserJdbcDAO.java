package dao;

import entity.User;
import util.DBHelper;
import com.mysql.jdbc.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {
    private static Connection connection;

    {
        connection = DBHelper.getMysqlConnection();
    }

    public UserJdbcDAO() {

    }

    @Override
    public User getUser(String login, String password) throws SQLException {
//        Connection connection;
//        connection = DBHelper.getMysqlConnection();
        User user = null;
        Statement stmt = null;
        ResultSet result = null;
        try {
            stmt = connection.createStatement();
            stmt.execute("SELECT * FROM users WHERE login = '" + login + "' AND  password = '" + password + "'");
            result = stmt.getResultSet();
            if (result.next()) {
                user = new User(
                        result.getLong(1),
                        result.getString(4),
                        result.getString(3),
                        result.getLong(2),
                        result.getString(5),
                        result.getString(6),
                        result.getString(7));
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (result != null) {
                    result.close();
                }
//                if (connection != null) {
//                    connection.close();
//                }
            } catch (Exception e) {
                throw new SQLException();
            }
        }
        return user;
    }

    public List<User> getAllUsers() throws SQLException {
//        Connection connection;
//        connection = DBHelper.getMysqlConnection();
        List<User> resultList = new ArrayList<>();
        Statement stmt = null;
        ResultSet result = null;
        try {
            stmt = connection.createStatement();
            stmt.execute("SELECT * FROM users");
            result = stmt.getResultSet();
            while (result.next()) {
                resultList.add(new User(
                        result.getLong(1),
                        result.getString(4),
                        result.getString(3),
                        result.getLong(2),
                        result.getString(5),
                        result.getString(6),
                        result.getString(7)));
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (result != null) {
                    result.close();
                }
//                if (connection != null) {
//                    connection.close();
//                }
            } catch (Exception e) {
                throw new SQLException();
            }
        }
        return resultList;
    }

    public void addUser(User user) throws SQLException {
//        Connection connection;
//        connection = DBHelper.getMysqlConnection();
        PreparedStatement ps = null;
        try {
            if (user != null) {
                ps = connection.prepareStatement("INSERT INTO users (name, lastName, age, login, password, role) VALUES((?), (?), (?), (?), (?), (?))");
                ps.setString(1, user.getName());
                ps.setString(2, user.getLastName());
                ps.setLong(3, user.getAge());
                ps.setString(4, user.getLogin());
                ps.setString(5, user.getPassword());
                ps.setString(6, user.getRole());
                ps.executeUpdate();
            }
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
//                if (connection != null) {
//                    connection.close();
//                }
            } catch (Exception e) {
                throw new SQLException();
            }
        }
    }

    public void deleteUser(Long id) throws SQLException {
//        Connection connection;
//        connection = DBHelper.getMysqlConnection();
        Statement stmt = null;
        try {
            String deleteQuery = "DELETE FROM users WHERE id = " + id;
            stmt = connection.createStatement();
            stmt.execute(deleteQuery);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
//                if (connection != null) {
//                    connection.close();
//                }
            } catch (Exception e) {
                throw new SQLException();
            }
        }
    }

    public void editUser(User user) throws SQLException {
//        Connection connection;
//        connection = DBHelper.getMysqlConnection();
        PreparedStatement checkStatement = null;
        PreparedStatement updateStatement = null;
        ResultSet result = null;
        try {
            String updateQuery = "UPDATE users SET name =?, lastName = ?, age = ?, login = ?, password = ?, role = ? WHERE id =?";
            updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, user.getName());
            updateStatement.setString(2, user.getLastName());
            updateStatement.setLong(3, user.getAge());
            updateStatement.setString(4, user.getLogin());
            updateStatement.setString(5, user.getPassword());
            updateStatement.setString(6, user.getRole());
            updateStatement.setLong(7, user.getId());
            updateStatement.execute();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (checkStatement != null) {
                    checkStatement.close();
                }
                if (updateStatement != null) {
                    updateStatement.close();
                }
//                if (connection != null) {
//                    connection.close();
//                }
            } catch (Exception e) {
                throw new SQLException();
            }
        }
    }
}
