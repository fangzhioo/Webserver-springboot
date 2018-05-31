package com.fangzhi.web;

import com.fangzhi.utils.MapCache;

public abstract class BaseController{

    public static String VERSION = "api/v1.0";

    protected MapCache cache = MapCache.single();


}