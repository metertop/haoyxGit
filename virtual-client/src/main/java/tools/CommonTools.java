package tools;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by weijianxing on 2015/6/30.
 */
public class CommonTools {
    /**
     *
     * @param year if year<0 the year update to past, else update future.
     * @param month if month<0 the month update to past, else update future.
     * @param day if day<0 the day update to past, else update future.
     * @return the date after modify.
     */
    public static Date modifyDate( int year , int month , int day){
        Calendar rightNow = Calendar.getInstance();
        //preset future date.
        rightNow.add(Calendar.YEAR,year);// add year
        rightNow.add(Calendar.MONTH,month);//add month
        rightNow.add(Calendar.DAY_OF_YEAR,day);//add day
        return rightNow.getTime();
    }
}
