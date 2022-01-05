package com.hfz.epidemicmanage.Util;

public class RedisKeyUtil {
    private static final String SPILT = ":";
    private static final String PREFIX_TICKET = "ticket";

    public static String getTicketKey(String ticket){
        return PREFIX_TICKET+SPILT+ticket;
    }
}
