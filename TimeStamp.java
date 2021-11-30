import java.sql.Timestamp;
import java.util.Date;

public class TimeStamp
{

    public static void printTimeStamp() {
        Date date= new Date();
        long currentTime = date.getTime();
        Timestamp ts = new Timestamp(currentTime);
        System.out.println("Current Time Stamp: "+ts);
    }
}
