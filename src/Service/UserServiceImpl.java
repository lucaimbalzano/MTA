package Service;

import Connections.DatabaseConnections;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserServiceImpl implements IUserService {
    @Override
    public void addUser(User user) {
        String preparedSql = "INSERT INTO CUSTOMERS (Name,Lastname,Age,Address,Phone,Action) VALUES (?,?,?)";
//        user.getName(),user.getLastname(),user.getAddress(), user.getPhone(),user.getAge(), user.getAction()
        PreparedStatement preparedStmt = null;
        DatabaseConnections databaseConnections = new DatabaseConnections();
        Connection conn = databaseConnections.getConnection();
        int cont = 0;
        try {
            //Creates a PreparedStatement object for sendingparameterized SQL statements to the database
            preparedStmt = conn.prepareStatement(preparedSql);
            preparedStmt.setString(++cont, user.getName());
            preparedStmt.setInt(++cont, user.getAge());
            preparedStmt.setInt(++cont, user.getSalary());
            result = preparedStmt.executeUpdate();
        }catch (){

        }
    }


}
