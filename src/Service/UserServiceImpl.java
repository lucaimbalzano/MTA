package Service;

import Connections.DatabaseConnections;
import model.User;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserServiceImpl implements IUserService {
    Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    public Boolean addUser(User user) {
        BasicConfigurator.configure();
        String preparedSql = "INSERT INTO CUSTOMERS (Name,Lastname,Age,Address,Phone,Action) VALUES (?,?,?)";
//        user.getName(),user.getLastname(),user.getAddress(), user.getPhone(),user.getAge(), user.getAction()
        int result = 0;
        int cont = 0 ;
        PreparedStatement preparedStmt = null;
        DatabaseConnections databaseConnections = new DatabaseConnections();
        Connection conn = databaseConnections.getConnection();
        try {
            //Creates a PreparedStatement object for sending parameterized SQL statements to the database
            preparedStmt = conn.prepareStatement(preparedSql);
            preparedStmt.setString(++cont, user.getName());
            preparedStmt.setString(++cont, user.getAge());
            preparedStmt.setString(++cont, user.getAddress());
            preparedStmt.setString(++cont, user.getPhone());
            preparedStmt.setString(++cont, user.getAction());
            result = preparedStmt.executeUpdate();
            if(result>0)
            {
                logger.debug("### User Added Successfully ###");
                return true;
            }

        } catch (SQLException e) {
            logger.debug("### Error occured inside addUser(): "+e.getMessage()+" ###");
            e.printStackTrace();
            logger.debug("### End stackTraceError ###");
        }
        logger.debug("### Error while adding the User ###");
        return false;
    }


}
