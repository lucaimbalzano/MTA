package Connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnections {
    private Connection conn;

    public Connection getConnection(){
        String dbName = "[mta]user";
        String dbUser = "root";
        String dbPsswd = "password";
        String url = "jdbc:mysql://localhost/"+ dbName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, dbUser, dbPsswd);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(" ### Exception Occurred: "+e.getMessage()+" ###");
            e.printStackTrace();
            System.out.println(" ### ###################################### ###");
        }
        return conn;
    }
}
