package com.hfz.epidemicmanage.Util;

public interface EpidemicConstant {
    /**
     * 激活成功
     */
    int ACTIVATION_SUCCESS = 1;

    /**
     * 激活失败
     */
    int ACTIVATION_FAILURE = 0;

    /**
     * 重复激活
     */
    int ACTIVATION_REPEAT = 2;
    /**
     * 信息完善
     */
    int INFORMATION_PERFECT = 3;

//    /**
//     * 未解决
//     */
//    int POST_UNRESOLVED = 0;
//    /**
//     * 已解决
//     */
//    int POST_RESOLVED = 1;


    /**
     * 未使用
     */
    int GOODS_UNUSE = 0;
    /**
     * 已使用
     */
    int GOODS_USE = 1;


    /**
     * 已读
     */
    int POST_READ = 1;
    /**
     * 未读
     */
    int POST_UNREAD = 0;

    /**
     * 未开始
     */
    int AC_UNSTART = 0;
    /**
     * 开始
     */
    int AC_START = 1;
    /**
     * 结束
     */
    int AC_END = 2;


}
