/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alien.businesslogic;

import alien.commonobjects.models.User;
import alien.commonobjects.models.UserTypes;
import alien.dataaccess.AdminAccessor;
import java.util.Collection;

/**
 *
 * @author Trent
 */
public class AdminManager {
    private final String userName;
    
    public AdminManager(String userName) {
        this.userName = userName;
    }
    
    public Collection<User> retrieveUsers() {
        Collection<User> users = null;
        
        try {
            users = AdminAccessor.retrieveUsers(UserTypes.User);
        } catch (Exception ex) { }
        
        return users;
    }
    
    public Collection<User> retrieveEmployees() {
        Collection<User> employees = null;
        
        try {
            employees = AdminAccessor.retrieveUsers(UserTypes.Employee);
        } catch (Exception ex) { }
        
        return employees;
    }
    
    public boolean demoteEmployee(String userName) {
        boolean flag = false;
        
        try {
            flag = 1 == AdminAccessor.removeRole(userName, UserTypes.Employee, this.userName);
            
            if (flag) {
                flag = 1 == AdminAccessor.addRole(userName, UserTypes.User, this.userName);
            }
        } catch (Exception ex) { flag = false; }
        
        return flag;
    }
    
    public boolean promoteUser(String userName) {
        boolean flag = false;
        
        try {
            flag = 1 == AdminAccessor.addRole(userName, UserTypes.Employee, this.userName);
            
            if (flag) {
                flag = 1 == AdminAccessor.removeRole(userName, UserTypes.User, this.userName);
            }
        } catch (Exception ex) { flag = false; }
        
        return flag;
    }
}
