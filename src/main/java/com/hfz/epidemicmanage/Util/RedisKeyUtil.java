package com.hfz.epidemicmanage.Util;

public class RedisKeyUtil {
    private static final String SPILT = ":";
    private static final String PREFIX_TICKET = "ticket";
    private static final String PREFIX_RECORD = "today";
    

    public static String getTicketKey(String ticket){
        return PREFIX_TICKET+SPILT+ticket;
    }
    public static String getTodayRecordKey(int accountid){
        return PREFIX_RECORD+SPILT+accountid;
    }
}
