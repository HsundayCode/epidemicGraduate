package com.hfz.epidemicmanage.Util;

import com.hfz.epidemicmanage.Config.QRCodeConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ErweimaUtil {

    @Value("${epidemicmanage.idlocal}")
    private String local;
    @Value("${epidemicmanage.tupianpath}")
        private  String tupianpath;

        public void createErweima(int code) throws Exception
        {

            String text = local+":8080/daka/"+code;

            String destPath = tupianpath+code+".jpg";//文件名

        //生成二维码
        QRCodeConfig.encode(text,destPath);
    }


}
