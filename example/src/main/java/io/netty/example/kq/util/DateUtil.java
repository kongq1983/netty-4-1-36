package io.netty.example.kq.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author kq
 * @date 2020-07-15 19:33
 * @since 2020-0630
 */
public class DateUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String now() {
        synchronized(sdf){
            try {
                return sdf.format(new Date());
            }catch (Exception e ){
                return null;
            }
        }
    }

    public static String dateToString(Date date) {
        synchronized(sdf){
            try {
                return sdf.format(date);
            }catch (Exception e ){
                return null;
            }
        }
    }

    public static Date stringToDate(String stringDate) throws ParseException{
        synchronized(sdf){
            return sdf.parse(stringDate);
        }
    }


}
