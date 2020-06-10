package dao;

import dao.UserDAO;
import dao.UserHibernateDAO;
import dao.UserJdbcDAO;
import util.DBHelper;
import util.ReadProperties;
import exception.UtilExc;

public class UserDaoFactory {
    public static UserDAO getDao() throws UtilExc {
        String daoType = new ReadProperties().getData("daotype");

        switch (daoType) {
            case "jdbc" :
                return new UserJdbcDAO();
            case "hibernate" :
                return new UserHibernateDAO(DBHelper.createSessionFactory());
            default:
                throw new UtilExc("enter the daotype in dao.properties");
        }
    }
}
