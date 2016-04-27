/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alien.helpers;

import alien.commonobjects.models.User;
import alien.commonobjects.models.UserRole;
import alien.commonobjects.models.UserTypes;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Trent
 */
public class SessionAssister {
    public static void clearErrors(HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        session.removeAttribute("error");
    }
    
    public static void addError(HttpServletRequest request, String error) {
        HttpSession session = request.getSession();
        
        session.setAttribute("error", error);
    }
    
    public static boolean loggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        return null != session.getAttribute("user");
    }
    
    public static boolean isRole(HttpServletRequest request, UserTypes userType) {
        boolean result = false;
        
        User user = retrieveSessionUser(request);
        
        if (null != user) {
            Collection<UserRole> rolesCollection = user.getUserRoles();
            UserRole[] userRoles = rolesCollection.toArray(new UserRole[rolesCollection.size()]);
            
            for (int i = 0; i < userRoles.length && !result; i++) {
                result = userRoles[i].getRoleType().equalsIgnoreCase(userType.name());
            }
        }
        
        return result;
    }
    
    public static User retrieveSessionUser(HttpServletRequest request) {
        HttpSession session = request.getSession();

        return (User)session.getAttribute("user");
    }
}
