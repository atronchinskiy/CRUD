package util;

import entity.User;
import com.mysql.jdbc.Driver;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import java.lang.InstantiationException;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

    private static SessionFactory sessionFactory;
    private static Configuration configuration;
    private static boolean flag = false;

    @SuppressWarnings("UnusedDeclaration")
    private static Configuration createMySqlConfiguration() {
        Configuration configuration = null;
        try {
            configuration = new Configuration();
            configuration.addAnnotatedClass(User.class);
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://DOM-CS-WS-0250:3306/webData");
            configuration.setProperty("hibernate.connection.username", "sys");
            configuration.setProperty("hibernate.connection.password", "");
            configuration.setProperty("hibernate.show_sql", "true");
        }catch (HibernateError e){
            e.printStackTrace();
        }
        return configuration;
    }

    public static SessionFactory createSessionFactory() {
        Configuration configuration = createMySqlConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());
            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:mysql://").        //db type
                    append("DOM-CS-WS-0250:").
                    append("3306/").                //port
                    append("webData?").
                    append("user=sys");          //login
            //append("password=");       //password
            System.out.println("URL: " + url + "\n");
            Connection connection = (Connection) DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}
