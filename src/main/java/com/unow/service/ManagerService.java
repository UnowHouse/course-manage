package com.unow.service;

import com.unow.common.ExceptionEnum;
import com.unow.common.MyException;
import com.unow.mapper.ManagerMapper;
import com.unow.pojo.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/*
 *  @项目名：  course-manager 
 *  @包名：    com.unow.service
 *  @文件名:   ManagerService
 *  @创建者:   Unow
 *  @创建时间:  2018/12/17 23:34
 *  @描述：    TODO
 */
@Service
public class ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    public void loginService(String username, String password,HttpSession session) {

        Manager manager = managerMapper.queryManager(username, password);
        if(manager == null){
            throw new MyException(ExceptionEnum.ERROR_LOGIN);
        }
        session.setAttribute("user",manager.getSickname());
    }


    public void registerService(String username, String password,String sickname) {

        Manager manager = new Manager();
        manager.setUsername(username);
        Manager one = managerMapper.selectOne(manager);
        if(one != null){
            throw new MyException(ExceptionEnum.ERROR_REGISTER);
        }
        manager.setPassword(password);
        manager.setSickname(sickname);
        int insert = managerMapper.insert(manager);
        if(insert != 1){
            throw new MyException(ExceptionEnum.ERROR_INSERT_MANAGER);
        }

    }

    public Map<String,String> isLogin(HttpSession session) {

        String sickname = (String) session.getAttribute("user");
        if(sickname == null){
            throw new MyException(ExceptionEnum.NOT_LOGIN);
        }
        Map<String,String> map = new HashMap<>();
        map.put("user",sickname);
        return map;

    }

    public void logoutService(HttpSession session) {
        if(session.getAttribute("user")!=null)
            session.removeAttribute("user");
    }
}
