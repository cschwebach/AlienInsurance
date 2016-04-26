/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alien.helpers;

import alien.commonobjects.models.User;
import alien.commonobjects.models.UserRole;
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
    
    public static boolean loggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        return null != session.getAttribute("user");
    }
    
    public static boolean isRole(HttpServletRequest request, String roleType) {
        boolean result = false;
        
        User user = retrieveSessionUser(request);
        
        if (null != user) {
            UserRole[] userRoles = (UserRole[])user.getUserRoles().toArray();
            
            boolean flag = false;
            
            for (int i = 0; i < userRoles.length && !flag; i++) {
                flag = userRoles[i].getRoleType().equalsIgnoreCase(roleType);
            }
        }
        
        return result;
    }
    
    public static User retrieveSessionUser(HttpServletRequest request) {
        HttpSession session = request.getSession();

        return (User)session.getAttribute("user");
    }
}
