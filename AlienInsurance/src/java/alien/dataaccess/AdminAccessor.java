/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alien.dataaccess;

import alien.commonobjects.models.User;
import alien.commonobjects.models.UserTypes;
import alien.helpers.ConvertTime;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import org.joda.time.DateTime;

/**
 *
 * @author Trent
 */
public class AdminAccessor {
    
    public static Collection<User> retrieveUsers(UserTypes userType) throws Exception {
        ArrayList<User> users = new ArrayList<>();
        
        Connection conn = DbConnection.getConnection();
        
        try {
            CallableStatement cmd = conn.prepareCall("{call sp_select_users_by_role_type(?, ?)}");
            
            cmd.setString(1, 
                    userType.name());
            cmd.setBoolean(2, 
                    true);
            
            ResultSet rs = cmd.executeQuery();
            
            User user;
            
            while (rs.next()) {
                user = new User(
                        rs.getString("user_name"));
                
                user.setFirstName(
                        rs.getString("first_name"));
                user.setLastName(
                        rs.getString("last_name"));
                user.setEmail(
                        rs.getString("email"));
                user.setDateCreated(
                        ConvertTime.getDateTime(
                                rs.getTimestamp("date_created")
                        ));
                user.setDateModified(
                        ConvertTime.getDateTime(
                                rs.getTimestamp("date_modified")
                        ));
                user.setModifiedBy(
                        rs.getString("modified_by"));
                
                users.add(user);
            }
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
    
    public static int removeRole(String userName, UserTypes userType, String modifiedBy) throws SQLException {
        int rowsAffected = 0;
        
        Connection conn = DbConnection.getConnection();
        
        try {
            CallableStatement cmd = conn.prepareCall("{call sp_retract_user_role(?, ?, ?, ?)}");
            
            cmd.setString(1, 
                    userName);
            cmd.setString(2, 
                    userType.name());
            cmd.setTimestamp(3, 
                    ConvertTime.getTimestamp(
                            new DateTime()
                    ));
            cmd.setString(4, 
                    modifiedBy);
            
            rowsAffected = cmd.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            try { 
                if (null != conn) 
                { conn.close(); }
            } catch (Exception ex) { throw ex; }
        }
        
        return rowsAffected;
    }
    
    public static int addRole(String userName, UserTypes userType, String modifiedBy) throws SQLException {
        int rowsAffected = 0;
        
        Connection conn = DbConnection.getConnection();
        
        try {
            CallableStatement cmd = conn.prepareCall("{call sp_assign_user_role(?, ?, ?, ?)}");
            
            cmd.setString(1, 
                    userName);
            cmd.setString(2, 
                    userType.name());
            cmd.setTimestamp(3, 
                    ConvertTime.getTimestamp(
                            new DateTime()
                    ));
            cmd.setString(4, 
                    modifiedBy);
            
            rowsAffected = cmd.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            try { 
                if (null != conn) 
                { conn.close(); }
            } catch (Exception ex) { throw ex; }
        }
        
        return rowsAffected;
    }
}


