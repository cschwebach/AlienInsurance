/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alien.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Trent
 */
class DbConnection {
    
    private static final String dbURL = "jdbc:mysql://localhost:3306/";
    private static final String dbName = "AlienInsurance";
    private static final String dbUserName = "alienuser";
    private static final String dbPassword = "Password_123";
    
    public static Connection getConnection() {
        Connection conn = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            conn = DriverManager.getConnection(
                    dbURL + dbName + "?useInformationSchema=true&useSSL=false",
                    dbUserName,
                    dbPassword);
        } catch (ClassNotFoundException | 
                 InstantiationException | 
                 IllegalAccessException | 
                 SQLException ex) { ex.printStackTrace(); } // return null connection
        
        return conn;
    }
}
