import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by w_2 on 2016-12-07.
 */
public class DateTest {
    @Test
    public void test() throws ParseException {
        String date="2016-12-07 18:58:40 GMT+08:00";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        df.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        Date date1=df.parse(date);
        System.out.println(df.format(date1));

    }
}
