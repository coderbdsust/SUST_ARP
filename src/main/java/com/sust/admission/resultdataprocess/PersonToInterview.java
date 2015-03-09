/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sust.admission.resultdataprocess;

/**
 *
 * @author user
 */
public class PersonToInterview {
    private String serial;
    private String position;
    private String examRoll;
    private String contactNo;

    public PersonToInterview(String serial, String position, String examRoll, String contactNo) {
        setSerial(serial);
        setPosition(position);
        setExamRoll(examRoll);
        setContactNo(contactNo);
    }
    
    public void setSerial(String serial){
        this.serial = serial;
    }
    
    public void setPosition(String position){
        this.position = position;
    }
    
    public void setExamRoll(String examRoll){
        this.examRoll = examRoll;
    }
    
    public void setContactNo(String contactNo){
        this.contactNo = contactNo;
    }
    
    
    public String getSerial(){
        return this.serial;
    }
    
    public String getPosition(){
        return this.position;
    }
    
    public String getExamRoll(){
        return this.examRoll;
    }
    
    public String getContactNo(){
        return this.contactNo;
    }
}
