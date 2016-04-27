/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alien.dataaccess;

import alien.commonobjects.models.User;
import alien.commonobjects.models.UserTypes;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Trent
 */
public class AdminAccessor {
    
    public static Collection<User> RetrieveUsers(UserTypes userType) throws Exception {
        ArrayList<User> users = new ArrayList<>();
        
        Connection conn = DbConnection.getConnection();
        
        try {
            CallableStatement cmd = conn.prepareCall("{call sp_select_users_by_role_type(?, ?)}");
            
            cmd.setString(1, 
                    retrieveUserTypeParam(userType));
            cmd.setBoolean(2, 
                    true);
        } catch (SQLException ex) {
            throw ex;
        } finally {
            try { 
                if (null != conn) 
                { conn.close(); }
            } catch (Exception ex) { throw ex; }
        }
        
        return users;
    }
    
    public static int DemoteEmployee(String userName) {
        int rowsAffected = 0;
        
        return rowsAffected;
    }
    
    public static int PromoteUser(String userName) {
        int rowsAffected = 0;
        
        return rowsAffected;
    }
    
    private static String retrieveUserTypeParam(UserTypes userType) {
        String result = "User";
        
        if (UserTypes.Administrator == userType) {
            result = "Administrator";
        } else if (UserTypes.Employee == userType) {
            result = "Employee";
        }
        
        return result;
    }
}


