/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alien.businesslogic;

import alien.commonobjects.models.User;
import alien.dataaccess.UserAccessor;
import org.joda.time.DateTime;

/**
 *
 * @author Trent
 */
public class UserManager {
    private final UserAccessor userAccessor;
    private User user;
    
    public UserManager() {
        userAccessor = UserAccessor.getInstance();
    }
    
    public User getCurrentUser() {
        return user;
    }
    
    public boolean attemptLogIn(String userName, String password) {
        user = null;
        
        try {
            user = userAccessor.retrieveUser(userName, password);
            
            if (null != user) {
                user.setUserRoles(
                        userAccessor.retrieveUserRoles(
                                user.getUserName()
                        ));
            }
        } catch (Exception ex) { user = null; }

        return null != user;
    }
    
    public boolean createUser(String userName, String password, 
            String firstName, String lastName, String email) {
        boolean flag = false;
        
        if (!isUser(userName)) {
            User newUser = new User(userName);
            
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setEmail(email);
            newUser.setDateCreated(new DateTime());
            
            try {
                flag = 1 == userAccessor.createUser(newUser, password);
                
                if (flag) {
                    flag = 1 == userAccessor.addUserRole(
                            userName, "User", new DateTime(), null);
                }
            } catch (Exception ex) { flag = false; }
        }
        
        return flag;
    }
    
    public boolean isUser(String userName) {
        boolean flag = true;
        
        try {
            flag = null != userAccessor.retrieveUser(userName);
        } catch (Exception ex) { }
        
        return flag;
    }
}
