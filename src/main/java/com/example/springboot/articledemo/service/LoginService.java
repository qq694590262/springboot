package com.example.springboot.articledemo.service;

import com.example.springboot.articledemo.bean.User;
import com.example.springboot.articledemo.util.DataBaseUtils;

/**
 * �û���¼�ķ�����
 * @author lenovo
 *
 */
public class LoginService {
    public User getUser(String username){
    	String sql = "select * from t_user where username = ?";
        User user = DataBaseUtils.queryForBean(sql, User.class, username);
        if(user == null){
            return null;
        }
        //System.out.println(user);
        return user;
    }
    
}
