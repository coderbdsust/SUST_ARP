/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sust.admission.service;

import java.util.ArrayList;

/**
 *
 * @author Biswajit Debnath
 */
public class UserAccount {
    
    private ArrayList userList;
    
    public UserAccount(){
        userList = new ArrayList<User>();
        initDefaultUser();
    }
    
    private void initDefaultUser(){
        userList.add(new User("admin.sust","sust"));
        userList.add(new User("admin","admin"));
        userList.add(new User("sust","sust"));
    }
    
    public boolean isUserValid(User loginUser){
        
        for(Object user: userList){
            if(((User)user).equals(loginUser)){
                return true;
            }
        }
        return false;
    }
    
}
