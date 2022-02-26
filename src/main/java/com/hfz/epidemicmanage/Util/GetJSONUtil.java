package com.hfz.epidemicmanage.Util;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class GetJSONUtil {

    public static String toJSON(int code, String msg,Map<String,Object> map)
    {
        JSONObject json = new JSONObject();
        json.put("code",code);
        json.put("msg",msg);
        if(map != null)
        {
            for (String key : map.keySet())
            {
                json.put(key,map.get(key));
            }
        }
        System.out.println(json.toJSONString());
        return json.toJSONString();
    }

    public static String toJSON(int code,String msg)
    {
        return toJSON(code,msg,null);
    }

    public static String toJSON(int code)
    {
        return toJSON(code,null,null);
    }
    public static String toJSON(String msg)
    {
        JSONObject json = new JSONObject();
        json.put("msg",json);
        return json.toJSONString();
    }
}
