package com.jhc.demo.Controller;

import com.jhc.demo.model.User;
import com.jhc.demo.service.UserService;
import org.apache.jasper.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author jhc on 2019/1/10
 */
@Controller
@RequestMapping("/")
public class TestController {
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        if(subject != null){
            subject.logout();
        }
        return "login";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }
    @RequestMapping("/admin")
    @ResponseBody
    public String admin(){
        return "admin success";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(){
        return "edit success";
    }

    @RequestMapping("/loginUser")
    public String loginUser(@RequestParam("username") String username, @RequestParam("password")String password, HttpSession httpSession){
        User user1 = userService.findByUserName(username);
        System.out.println(user1.getUsername() + user1.getPassword() );
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        try{

            subject.login(usernamePasswordToken);

            User user = (User)subject.getPrincipal();

            httpSession.setAttribute("user",user);
            return "index";
        }catch (Exception e){
            return "login";
        }
    }
}
