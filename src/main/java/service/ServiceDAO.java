package service;

import entity.User;
import exception.DBException;
import java.util.List;

public interface ServiceDAO {
    public List<?> getAllUsersService() throws DBException;

    public boolean addUserService(User user) throws DBException;

    public boolean deleteUserService(Long id) throws DBException;

    public boolean editUserService(User user) throws DBException;
}
