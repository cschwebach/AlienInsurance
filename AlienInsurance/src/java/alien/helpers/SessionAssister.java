/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alien.helpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Trent
 */
public class SessionAssister {
    public static void ClearErrors(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("error");
    }
}
