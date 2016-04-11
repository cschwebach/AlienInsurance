/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alien.helpers;

import java.sql.Timestamp;
import org.joda.time.DateTime;

/**
 *
 * @author Trent
 */
public class ConvertTime {
    public static Timestamp getTimestamp(DateTime dateTime) {
        Timestamp timestamp = null;
        
        if (null != dateTime) {
            timestamp = new Timestamp(dateTime.getMillis());
        }
        
        return timestamp;
    }
    
    public static DateTime getDateTime(Timestamp timestamp) {
        DateTime dateTime = null;
        
        if (null != timestamp) {
            dateTime = new DateTime(timestamp.getTime());
        }
        
        return dateTime;
    }
}
