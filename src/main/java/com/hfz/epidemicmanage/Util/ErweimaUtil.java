package com.hfz.epidemicmanage.Util;

import com.hfz.epidemicmanage.Config.QRCodeConfig;

public class ErweimaUtil {

    public static void createErweima(int code) throws Exception
    {

        String text = "http://localhost:8080/daka/"+code;

        String destPath = "F:/epidemicmanage/"+code+".jpg";//文件名
        //生成二维码
        QRCodeConfig.encode(text,destPath);
    }


}
