/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alien.helpers;

import com.mysql.jdbc.StringUtils;

/**
 *
 * @author Trent
 */
public class StringAssister {
    public static String checkBlank(String value, int length) {
        if (StringUtils.isEmptyOrWhitespaceOnly(value) || value.length() > length) {
            value = "HELP ME! THE GOVERNMENT'S MIND CONTROL WORKS!";
        } 
        
        return value;
    }
}
