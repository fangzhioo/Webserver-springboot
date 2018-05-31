package com.fangzhi.web;

import java.util.HashMap;
import java.util.Map;

import com.fangzhi.utils.CommonConstants;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/hello")
public class HelloController extends BaseController{
    
    @RequestMapping(method = RequestMethod.GET)
    public Map sayHello(){
        Map map = new HashMap<>();
        map.put(CommonConstants.RESP_CODE, CommonConstants.SUCCESS);
        map.put(CommonConstants.RESP_MESSAGE, "开启成功");
        map.put(CommonConstants.RESULT, "hello !");
        return map;
    }


}