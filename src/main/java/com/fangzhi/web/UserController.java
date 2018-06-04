package com.fangzhi.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fangzhi.domain.LoginInfo;
import com.fangzhi.domain.User;
import com.fangzhi.service.UserService;
import com.fangzhi.utils.CommonConstants;
import com.fangzhi.utils.Tools;

@RestController
@CrossOrigin  // 允许跨域
@RequestMapping("api/v1/users")
public class UserController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService; 

    // static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @ApiOperation(value="获取用户列表",notes="获取用户列表")
    @RequestMapping(value="/all",method=RequestMethod.GET)
    public Map getUserAll(){
        Map map = new HashMap<>();
        try{
            List<User> r = userService.findAll();      
            map.put(CommonConstants.RESP_CODE, CommonConstants.SUCCESS);
            map.put(CommonConstants.RESP_MESSAGE, "获取用户列表成功！");
            map.put(CommonConstants.RESULT, r);
        }catch(Exception e){
            map.put(CommonConstants.RESP_CODE, CommonConstants.FALIED);
            map.put(CommonConstants.RESP_MESSAGE, "获取失败");
            map.put(CommonConstants.RESULT, null);
        }
        return map;
    }

    @ApiOperation(value = "分页获取用户列表",notes = "分页获取用户列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name="page",value="当前页 从0开始",required=false,dataType="Integer",paramType="path"),
        @ApiImplicitParam(name="size",value="每页大小 默认20",required=false,dataType="Integer",paramType="path")
    })
    @RequestMapping(value = "/list",method=RequestMethod.GET)
    public Map getUserList(Pageable pageable){
        Map map = new HashMap<>();    
        try {
            List<User> r = userService.findByPage(pageable).getContent();
            map.put(CommonConstants.RESP_CODE, CommonConstants.SUCCESS);
            map.put(CommonConstants.RESP_MESSAGE, "获取用户列表成功！");
            map.put(CommonConstants.RESULT, r);
        } catch (Exception e) {
            map.put(CommonConstants.RESP_CODE, CommonConstants.FALIED);
            map.put(CommonConstants.RESP_MESSAGE, "获取失败");
            map.put(CommonConstants.RESULT, null);
        }
        return map;
    }

    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public Map postUser(@RequestBody User user) {
        Map map = new HashMap();
        try {
            // 是否已经注册
            String username = user.getUsername();
            if(userService.findByUsername(username) != null){
                map.put(CommonConstants.RESP_CODE, CommonConstants.FALIED);
                map.put(CommonConstants.RESP_MESSAGE, "用户名-"+username+"-已经被注册了!");
                map.put(CommonConstants.RESULT, false);
                return map;
            }
            // 密码加密
            String pass = user.getPassword();
            user.setPassword(Tools.MD5encode(pass));
            // 注册时间
            Date now = new Date();
            user.setCreated(now.toLocaleString());
            // 设置为普通用户
            user.setRole(1);
            
            userService.save(user);
            map.put(CommonConstants.RESP_CODE, CommonConstants.SUCCESS);
            map.put(CommonConstants.RESP_MESSAGE, "注册成功！");
            map.put(CommonConstants.RESULT, null);
        } catch (Exception e) {
            map.put(CommonConstants.RESP_CODE, CommonConstants.FALIED);
            map.put(CommonConstants.RESP_MESSAGE, "注册失败！");
            map.put(CommonConstants.RESULT, false);
        }
        return map;
    }

    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "userid", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value="/find/{userid}", method=RequestMethod.GET)
    public Map getUserById(@PathVariable Long userid) {
        Map map = new HashMap<>();
        try {
            User u = userService.findUser(userid);
            map.put(CommonConstants.RESP_CODE, CommonConstants.SUCCESS);
            map.put(CommonConstants.RESP_MESSAGE, "查询成功！");
            map.put(CommonConstants.RESULT, u);
        } catch (Exception e) {
            //TODO: handle exception
            map.put(CommonConstants.RESP_CODE, CommonConstants.FALIED);
            map.put(CommonConstants.RESP_MESSAGE, "查询失败！");
            map.put(CommonConstants.RESULT, null);
        }
        return map;

    }

    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "用户ID", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value="/update/{userid}", method=RequestMethod.PUT)
    public Map putUser(@PathVariable Long userid, @RequestBody User user) {
        Map map = new HashMap<>();

        try {
            User u = userService.findUser(userid);
            u.setUsername(user.getUsername()==null?u.getUsername():user.getUsername());
            // u.setPassword(user.getPassword());
            u.setBirthday(user.getBirthday()==null?u.getBirthday():user.getBirthday());
            u.setEmail(user.getEmail()==null?u.getEmail():user.getEmail());
            u.setIntro(user.getIntro()==null?u.getIntro():user.getIntro());
            userService.save(u);
            map.put(CommonConstants.RESP_CODE, CommonConstants.SUCCESS);
            map.put(CommonConstants.RESP_MESSAGE, "更新成功！");
            map.put(CommonConstants.RESULT, true);

        } catch (Exception e) {
            //TODO: handle exception
            map.put(CommonConstants.RESP_CODE, CommonConstants.FALIED);
            map.put(CommonConstants.RESP_MESSAGE, "更新失败！");
            map.put(CommonConstants.RESULT, false);
        }

        return map;
    }

    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "userid", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value="/remove/{userid}", method=RequestMethod.DELETE)
    public Map deleteUser(@PathVariable Long userid) {
        Map map = new HashMap<>();
        try {
            userService.deleteUserById(userid);
            map.put(CommonConstants.RESP_CODE, CommonConstants.SUCCESS);
            map.put(CommonConstants.RESP_MESSAGE, "删除成功！");
            map.put(CommonConstants.RESULT, true);
        } catch (Exception e) {
            //TODO: handle exception
            map.put(CommonConstants.RESP_CODE, CommonConstants.FALIED);
            map.put(CommonConstants.RESP_MESSAGE, "删除失败！");
            map.put(CommonConstants.RESULT, false);
        }

        return map;
    }

    @ApiOperation(value="用户登录",notes="根据获取的User对象匹配登录验证")
    @ApiImplicitParam(name="loginInfo",value="包含用户对象和是否记住密码的数据",required=true,dataType="LoginInfo")
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public Map login(@RequestBody LoginInfo loginInfo,
                    HttpServletRequest request, 
                    HttpServletResponse response) {
        User user = loginInfo.getUser();
        Boolean remmberMe = loginInfo.getRemember();
        Map map = new HashMap<>();
        String username = user.getUsername();
        String pass = user.getPassword();    
        if(pass==null || username == null){
            map.put(CommonConstants.RESP_CODE, CommonConstants.FALIED);
            map.put(CommonConstants.RESP_MESSAGE, "用户名和密码不能为空！");
            map.put(CommonConstants.RESULT, false); 
            return map;
        }
        
        pass = Tools.MD5encode(pass);
        Integer error_count = cache.get("login_error_count")==null ? 1 : cache.get("login_error_count");
        try {
            if (error_count > 3) {
                cache.set("login_error_count", error_count, 10 * 60);
                map.put(CommonConstants.RESP_CODE, CommonConstants.FALIED);
                map.put(CommonConstants.RESP_MESSAGE, "您输入密码已经错误超过3次，请10分钟后尝试!");
                map.put(CommonConstants.RESULT, false); 
                return map;
            }

            if(null == userService.findByUsername(username)){
                map.put(CommonConstants.RESP_CODE, CommonConstants.FALIED);
                map.put(CommonConstants.RESP_MESSAGE, "找不到名为-"+username+"-的用户!");
                map.put(CommonConstants.RESULT, false); 
                return map;
            }

            User loginUser =  userService.findByUsernameAndPassword(username,pass);
            if(loginUser == null){
                error_count = null == error_count ? 1 : error_count + 1;
                map.put(CommonConstants.RESP_CODE, CommonConstants.FALIED);
                map.put(CommonConstants.RESP_MESSAGE, "密码错误!");
                map.put(CommonConstants.RESULT, false); 
                return map;
            }
            map.put(CommonConstants.RESP_CODE, CommonConstants.SUCCESS);
            map.put(CommonConstants.RESP_MESSAGE, "登录成功");
            map.put(CommonConstants.RESULT, loginUser);
            // 登陆成功后...
            request.getSession().setAttribute(CommonConstants.LOGIN_SESSION_KEY, loginUser);
            if(remmberMe){
                Tools.setCookie(response, loginUser.getUsername());
            }
        } catch (Exception e) {
            map.put(CommonConstants.RESP_CODE, CommonConstants.FALIED);      
            map.put(CommonConstants.RESP_MESSAGE, "登录失败!");  
            map.put(CommonConstants.RESULT, false); 
        }
        return map;
    }


  
}