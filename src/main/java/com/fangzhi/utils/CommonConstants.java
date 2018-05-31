package com.fangzhi.utils;

public class CommonConstants{
    /***返回码**/
	public static final String RESP_CODE  = "resp_code";
	/***返回描述**/
	public static final String RESP_MESSAGE = "resp_message";
    
    
    /*参数不完整**/
	public static final String ERROR_VERI_CODE = "000002";
	/*参数错误**/
    public static final String ERROR_PARAM  = "000001";
    /**成功*/
    public static final String SUCCESS = "000000";
   /***失败*/
    public static final String FALIED  = "999999";
    /**结果**/
    public static final String RESULT = "result";
    
    // LOGIN_SESSION_KEY 
    public static final String LOGIN_SESSION_KEY = "login_user";

    // USER_IN_COOKIE
    public static final String USER_IN_COOKIE = "S_L_ID";

     /**
     * aes加密加盐
     */
    public static String AES_SALT = "0123456789abcdef";
    
    /***
     * 同一文章 在时间内无论点击多少次只能算一次阅读
     */
    public static Integer HITS_LIMIT_TIME = 7200;
}