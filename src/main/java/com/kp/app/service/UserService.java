/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kp.app.service;

import com.kp.app.model.UserAccount;
import com.kp.app.model.UserModel;
import com.kp.app.model.UserStatus;
import com.kp.app.model.UserType;
import com.kp.app.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rohan
 */
@Service
public class UserService
{
    @Autowired
    private UserRepository repo;
    
    private ArrayList<UserType> userTypeList;
    
    public UserService()
    {
        System.out.println("Service default constructor");
        setUserTypeList();
    }
    
    private void setUserTypeList()
    {
        ArrayList<UserType> list = new ArrayList<>();
        UserType customer = new UserType(UserType.TYPE_CUSTOMER, "Customer", "This is Customer");
        UserType support = new UserType(UserType.TYPE_SUPPORT, "Support Engineer", "This is Support Engineer");
        UserType admin = new UserType(UserType.TYPE_ADMIN, "Admin", "This is Admin");
        list.add(customer);
        list.add(support);
        list.add(admin);
        userTypeList=list;
        System.out.println("UserTypeList created");
    }

    public ArrayList<UserType> getUserTypeList() 
    {
        return userTypeList;
    }
    
    public UserModel createUserAccount(String name,int userTypeId,String phone,String email,String password)
    {
        UserStatus status = new UserStatus();
        UserModel model = new UserModel();
        
        UserStatus emailStatus = emailVerification(email);
        
        if(emailStatus.getStatusCode()==0)
        {
            UserAccount user = new UserAccount();
            user.setName(name);
            user.setUserTypeId(userTypeId);
            user.setPhone(phone);
            user.setEmail(email);
            user.setPassword(password);
            repo.save(user);
            
            status.setStatusCode(UserStatus.SUCCESS);
            status.setStatusDescription("Sign Up Successful.");
            
            model.setUser(user);
            model.setStatus(status);
            
            return model;
        }
        else
        {
            status.setStatusCode(UserStatus.FAILURE);
            status.setStatusDescription("User Account with this email already exists.");
            
            model.setStatus(status);
            
            return model;
        }
    }
    
    private List<UserAccount> getAllUserAccount()
    {
        List<UserAccount> list = repo.findAll();
        return list;
    }
    
    public UserStatus emailVerification(String email)
    {
        List<UserAccount> list = getAllUserAccount();
        boolean b = false;
        UserStatus status = new UserStatus();
        if(list!=null)
        {
            for(UserAccount ele : list)
            {
                String e = ele.getEmail();
                if(e.equals(email))
                {
                    b=true;
                    break;
                }
            }
            if(b==true)
            {
                status.setStatusCode(UserStatus.SUCCESS);
                status.setStatusDescription("Email found. Kindly Login.");
            }
            else
            {
                status.setStatusCode(UserStatus.FAILURE);
                status.setStatusDescription("Email not found. Kindly Sign Up.");
            }
        }
        return status;
    }
    
    public UserModel loginUserAccount(String email,String password)
    {
        List<UserAccount> list = getAllUserAccount();
        UserAccount u = null;
        UserStatus status = new UserStatus();
        UserModel model = new UserModel();
        if(list!=null)
        {
            for(UserAccount ele : list)
            {
                String e = ele.getEmail();
                String p = ele.getPassword();
                if(e.equals(email) && p.equals(password))
                {
                    u=ele;
                    break;
                }
            }
            if(u!=null)
            {
                status.setStatusCode(UserStatus.SUCCESS);
                status.setStatusDescription("Logged in Successfully.");
                model.setStatus(status);
                model.setUser(u);
            }
            else
            {
                status.setStatusCode(UserStatus.FAILURE);
                status.setStatusDescription("Invalid Email Id or password");
                model.setStatus(status);
                model.setUser(u);
            }
        }
        return model;
    }
    public UserModel updateUserAccount(String name,int userTypeId,String phone,String email,String password)
    {
        List<UserAccount> list = getAllUserAccount();
        UserAccount u = null;
        UserStatus status = new UserStatus();
        UserModel model = new UserModel();
        if(list!=null)
        {
            for(UserAccount ele : list)
            {
                String e = ele.getEmail();
                String p = ele.getPassword();
                if(e.equals(email) && p.equals(password))
                {
                    ele.setName(name);
                    ele.setPhone(phone);
                    repo.save(ele);
                    u=ele;
                    break;
                }
            }
            if(u!=null)
            {
                status.setStatusCode(UserStatus.SUCCESS);
                status.setStatusDescription("Data updated Successfully.");
                model.setStatus(status);
                model.setUser(u);
            }
            else
            {
                status.setStatusCode(UserStatus.FAILURE);
                status.setStatusDescription("Invalid Email Id or password");
                model.setStatus(status);
                model.setUser(u);
            }
        }
        return model;
    }
    
    public UserModel changePassword(String email,String oldpassword,String newpassword)
    {
        List<UserAccount> list = getAllUserAccount();
        UserStatus status = new UserStatus();
        UserModel model = new UserModel();
        UserAccount usr = null;
        for(UserAccount ele : list)
        {
            String e = ele.getEmail();
            String p = ele.getPassword();
            if(e.equals(email) && p.equals(oldpassword))
            {
                ele.setPassword(newpassword);
                repo.save(ele);
                usr=ele;
                break;
            }
        }
        if(usr!=null)
        {
            status.setStatusCode(UserStatus.SUCCESS);
            status.setStatusDescription("Password Changed Successfully.");
            model.setStatus(status);
            model.setUser(usr);
        }
        else
        {
            status.setStatusCode(UserStatus.FAILURE);
            status.setStatusDescription("Invalid Email Id or password");
            model.setStatus(status);
            model.setUser(usr);
        }
        return model;
    }
    
    public ArrayList<UserAccount> getAllSupportEngineers()
    {
        List<UserAccount> list = getAllUserAccount();
        ArrayList<UserAccount> supportList = new ArrayList<>();
        for(UserAccount ele : list)
        {
            int userid = ele.getUserTypeId();
            if(userid==UserType.TYPE_SUPPORT)
            {
                supportList.add(ele);
            }
        }
        return supportList;
    }
    
    public ArrayList<UserAccount> getUsersAndSupportEngineersList()
    {
        List<UserAccount> list = getAllUserAccount();
        ArrayList<UserAccount> usList = new ArrayList<>();
        list.forEach((ele) -> {
            int type = ele.getUserTypeId();
            if (type == UserType.TYPE_CUSTOMER || type== UserType.TYPE_SUPPORT)
            {
                usList.add(ele);
            }
        });
        return usList;
    }
    
}