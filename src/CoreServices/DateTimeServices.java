/*
 * Date and Time Services
 */
package CoreServices;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeServices {
    DateFormat Format;
    Date DateTime;
    
    public String Date(){
       String Date = null;
            DateTime = new Date();
            Format = new SimpleDateFormat("dd/MM/yyyy");
            Date = Format.format(DateTime);
            DateTime = null;
            Format = null;
        return Date;
    }
    
    public String Time(){
       String Time = null;
            DateTime = new Date();
            Format = new SimpleDateFormat("HH:mm:ss");
            Time = Format.format(DateTime);
            DateTime = null;
            Format = null;
        return Time;
    }
}
