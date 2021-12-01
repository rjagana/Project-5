import java.sql.Timestamp;
import java.util.Date;

public class TimeStamp
{

    public static String printTimeStamp() {
        Date date= new Date();
        long currentTime = date.getTime();
        Timestamp ts = new Timestamp(currentTime);
        String timeString = "Current Time Stamp: "+ ts;
        return timeString;
    }
}
