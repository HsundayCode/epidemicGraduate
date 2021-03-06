package com.hfz.epidemicmanage.Util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.UUID;

@Component
public class EpidemicUtil {
    public static String generateUUID(){return UUID.randomUUID().toString().replaceAll("-","");}

    public static String getmd5(String key){
        if(StringUtils.isBlank(key))
        {
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
}
