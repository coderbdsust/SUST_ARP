/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sust.admission.constant;

import com.sust.admission.databaseservice.DatabaseService;

/**
 *
 * @author Biswajit Debnath
 */
public class DbUser extends DatabaseService{
    public static final String USERNAME="root";
    public static final String PASSWORD="coderbd";
    public static final String DATABASETYPE="com.mysql.jdbc.Driver";
    public static final String DATABASEURL="jdbc:mysql://localhost:3306/sust_arp"; 
    
    
}
