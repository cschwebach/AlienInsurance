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
    
    public Collection<User> RetrieveUsers() {
        Collection<User> users = null;
        
        try {
            users = AdminAccessor.RetrieveUsers(UserTypes.User);
        } catch (Exception ex) { }
        
        return users;
    }
    
    public Collection<User> RetrieveEmployees() {
        Collection<User> employees = null;
        
        try {
            employees = AdminAccessor.RetrieveUsers(UserTypes.Employee);
        } catch (Exception ex) { }
        
        return employees;
    }
    
    public boolean DemoteEmployee(String userName) {
        boolean flag = false;
        
        return flag;
    }
    
    public boolean PromoteUser(String userName) {
        boolean flag = false;
        
        return flag;
    }
}
