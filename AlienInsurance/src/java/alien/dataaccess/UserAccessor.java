/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alien.dataaccess;

import alien.commonobjects.models.User;
import alien.commonobjects.models.UserRole;
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
public class UserAccessor {
    private static UserAccessor userAccessor = null;
    
    public static UserAccessor getInstance() {
        if (null == userAccessor) {
            userAccessor = new UserAccessor();
        }

        return userAccessor;
    }
    
    private UserAccessor() { }
    
    public User retrieveUser(String userName, String password) throws SQLException {
        User user = null;
        
        Connection conn = DbConnection.getConnection();
        
        try {
            CallableStatement cmd = conn.prepareCall("{call sp_select_user_on_password(?, ?)}");
            
            cmd.setString(1, userName);
            cmd.setString(2, password);
            
            ResultSet rs = cmd.executeQuery();
            
            if (rs.next()) {
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
                user.setActive(
                        rs.getBoolean("active"));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            try { 
                if (null != conn) 
                { conn.close(); }
            } catch (Exception ex) { throw ex; }
        }
        
        return user;
    }
    
    public User retrieveUser(String userName) throws SQLException {
        User user = null;
        
        Connection conn = DbConnection.getConnection();
        
        try {
            CallableStatement cmd = conn.prepareCall("{call sp_select_user(?)}");
            
            cmd.setString(1, 
                    userName);
            
            ResultSet rs = cmd.executeQuery();
            
            if (rs.next()) {
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
                user.setActive(
                        rs.getBoolean("active"));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            try { 
                if (null != conn) 
                { conn.close(); }
            } catch (Exception ex) { throw ex; }
        }
        
        return user;
    }
    
    public int createUser(User user, String password) throws SQLException {
        int rowsAffected = 0;
        
        Connection conn = DbConnection.getConnection();
        
        try {
            CallableStatement cmd = conn.prepareCall("{call sp_insert_user(?, ?, ?, ?, ?, ?)}");
            
            cmd.setString(1, 
                    user.getUserName());
            cmd.setString(2, 
                    password);
            cmd.setString(3, 
                    user.getFirstName());
            cmd.setString(4, 
                    user.getLastName());
            cmd.setString(5, 
                    user.getEmail());
            cmd.setTimestamp(6, 
                    ConvertTime.getTimestamp(
                            user.getDateCreated()
                    ));
            
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
    
    public Collection<UserRole> retrieveUserRoles(String userName) throws SQLException {
        Collection<UserRole> userRoles = new ArrayList<>();
        
        Connection conn = DbConnection.getConnection();
        
        try {
            CallableStatement cmd = conn.prepareCall("{call sp_select_user_roles(?, ?)}");
            
            cmd.setString(1, 
                    userName);
            cmd.setBoolean(2, 
                    true);
            
            ResultSet rs = cmd.executeQuery();
            
            UserRole userRole;

            while (rs.next()) {
                userRole = new UserRole(
                        rs.getString("role_type"));

                userRole.setDateCreated(
                        ConvertTime.getDateTime(
                                rs.getTimestamp("date_created")
                        ));
                userRole.setDateModified(
                        ConvertTime.getDateTime(
                                rs.getTimestamp("date_modified")
                        ));
                userRole.setModifiedBy(
                        rs.getString("modified_by"));
                userRole.setDescription(
                        rs.getString("description"));

                userRoles.add(userRole);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            try { 
                if (null != conn) 
                { conn.close(); }
            } catch (Exception ex) { throw ex; }
        }
        
        return userRoles;
    }
    
    public int addUserRole(String userName, String roleType, DateTime dateCreated) throws SQLException {
        int rowsAffected = 0;
        
        Connection conn = DbConnection.getConnection();

        try {
            CallableStatement cmd = conn.prepareCall(
                    "{call sp_assign_user_role(?, ?, ?)}");
            
            cmd.setString(1, 
                    userName);
            cmd.setString(2, 
                    roleType);
            cmd.setTimestamp(3, 
                    ConvertTime.getTimestamp(dateCreated));
            
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
