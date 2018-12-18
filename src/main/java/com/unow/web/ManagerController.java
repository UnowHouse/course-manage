package com.unow.web;

import com.unow.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/*
 *  @项目名：  course-manager 
 *  @包名：    com.unow.web
 *  @文件名:   ManagerController
 *  @创建者:   Unow
 *  @创建时间:  2018/12/17 23:29
 *  @描述：    TODO
 */
@RestController
@RequestMapping("manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    /**
     * 登录
     * @param session
     * @param username
     * @param password
     * @return
     */
    @PostMapping("login")
    public ResponseEntity<Void> login(HttpSession session,
                                      @RequestParam(name = "username")String username,
                                      @RequestParam(name = "password")String password){
        managerService.loginService(username,password,session);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
    @PostMapping("register")
    public ResponseEntity<Void> register(@RequestParam(name = "username")String username,
                                         @RequestParam(name = "password")String password,
                                         @RequestParam(name = "sickname",defaultValue = "小明")String sickname){
        managerService.registerService(username,password,sickname);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 识别登录
     * @param session
     * @return
     */
    @GetMapping("isLogin")
    public ResponseEntity<Map<String,String>> detectLogin(HttpSession session){
        Map<String, String> user = managerService.isLogin(session);
        return ResponseEntity.ok(user);
    }

    /**
     * 注销
     * @param session
     * @return
     */
    @GetMapping("logout")
    public ResponseEntity<Void> logout(HttpSession session){

        managerService.logoutService(session);
        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
