package com.hfz.epidemicmanage.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static boolean getDifferentDateGap(String date1, String date2)
    {
        if(date1 ==null || date2 ==null)
        {
            return true;
        }
        String[] before = date1.split("-");
        int beforeYear = Integer.parseInt(before[0]);
        int beforeMonth = Integer.parseInt(before[1]);
        int beforeDay = Integer.parseInt(before[2]);
        String[] after = date2.split("-");
        int afterYear = Integer.parseInt(after[0]);
        int afterMonth = Integer.parseInt(after[1]);
        int afterDay = Integer.parseInt(after[2]);
        if(afterMonth > beforeMonth)
        {
            return true;
        }
        if((afterDay - beforeDay) >=1)
        {
            return true;
        }
        return false;

    }
}
