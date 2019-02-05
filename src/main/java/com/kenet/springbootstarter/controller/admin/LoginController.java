package com.kenet.springbootstarter.controller.admin;

import com.kenet.springbootstarter.entity.Result;
import com.kenet.springbootstarter.entity.User;
import com.kenet.springbootstarter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;



    @GetMapping(value = "/login")
    public String login() {
        return "home/login";
    }

    @GetMapping(value = "/register")
    public String register() {
        return "home/register";
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        attributes.getRequest().getSession().removeAttribute("user");
        return "home/login";
    }

    /*
    * 登录验证通过后将登录信息存入到session域对象中
    * */
    @RequestMapping("/login")
    @ResponseBody
    public Result login(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println(username +":" + password);
        User user = userService.findByName(username);
        if(user != null) {
            if(user.getPassword().equals(password)) {
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                attributes.getRequest().getSession().setAttribute("user",user);
                return new Result(true,user.getUsername());
            }
        }
        return new Result(false,"登录失败");
    }

    /*
    * 注册
    * */

}
