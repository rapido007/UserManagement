/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kp.app.controller;

import com.kp.app.model.UserAccount;
import com.kp.app.model.UserModel;
import com.kp.app.model.UserStatus;
import com.kp.app.model.UserType;
import com.kp.app.service.UserService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Rohan
 */
@CrossOrigin(origins = "http://localhost:8383")
@RestController
@RequestMapping("user")
public class UserController
{
    @Autowired
    private UserService service;
    
    public UserController()
    {
        System.out.println("Controller default constuctor");
    }
    
    @RequestMapping("getUserTypeList")
    public ArrayList<UserType> getUserTypeList()
    {
        ArrayList<UserType> list = service.getUserTypeList();
        return list;
    }
    
    @RequestMapping("signup")
    public UserModel signUpUserAccount(@RequestParam String name,@RequestParam int userTypeId,@RequestParam String phone,@RequestParam String email,@RequestParam String password)
    {
        UserModel model = service.createUserAccount(name, userTypeId, phone, email, password);
        return model;
    }
    
    @RequestMapping("emailVerification")
    public UserStatus emailVerification(@RequestParam String email)
    {
        UserStatus status = service.emailVerification(email);
        return status;
    }
    
    @RequestMapping("login")
    public UserModel loginUserAccount(@RequestParam String email,@RequestParam String password)
    {
        UserModel model = service.loginUserAccount(email, password);
        return model;
    }
    
    @RequestMapping("update")
    public UserModel updateUserAccount(@RequestParam String name,@RequestParam int userTypeId,@RequestParam String phone,@RequestParam String email,@RequestParam String password)
    {
        UserModel model = service.updateUserAccount(name, userTypeId, phone, email, password);
        return model;
    }
    
    @RequestMapping("changepassword")
    public UserModel changePassword(@RequestParam String email,@RequestParam String oldpassword,@RequestParam String newpassword)
    {
        UserModel model = service.changePassword(email, oldpassword, newpassword);
        return model;
    }
    
    @RequestMapping("getallsupportengineers")
    public ArrayList<UserAccount> getAllSupportList()
    {
        ArrayList<UserAccount> list = service.getAllSupportEngineers();
        return list;
    }
    @RequestMapping("getallsupportuser")
    public ArrayList<UserAccount> getAllSupportAndUsers()
    {
        ArrayList<UserAccount> list = service.getUsersAndSupportEngineersList();
        return list;
    }
}
