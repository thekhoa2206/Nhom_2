package com.vuw17.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Common {
    private static final Logger LOGGER = LoggerFactory.getLogger(Common.class.toString());

    public  static String GeneratePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        password = encoder.encode(password);
        return password;
    }
    public static String getDate( Long milliSeconds){
        String dateFormat=  "dd/MM/yyyy hh:mm:ss.SSS";
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public static String getDateByMilliSeconds( long milliSeconds){
        String dateFormat=  "hh:mm  dd/MM/yyyy ";
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
    public static String getDateByMilliSecond( long milliSeconds){
        String dateFormat=  "yyyy-MM-dd";
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }


    public static long getMilliSeconds( String myDate) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(myDate);
        long millis = date.getTime();
        return millis;
    }

    public  static String getStringPriceVN( double s){
        try {
            Locale locale = new Locale("vi", "VN");
            NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
            return fmt.format(s);
        }catch (IllegalArgumentException e){
            LOGGER.error("ERROR: {} | Lỗi đổi format");
        }
        return null;
    }
    public  static String getStringPriceVNBigDecimal( BigDecimal s){
        try {
            Locale locale = new Locale("vi", "VN");
            NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
            return fmt.format(s);
        }catch (IllegalArgumentException e){
            LOGGER.error("ERROR: {} | Lỗi đổi format");
        }
        return null;
    }

}