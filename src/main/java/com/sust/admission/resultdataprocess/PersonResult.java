package com.sust.admission.resultdataprocess;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Biswajit Debnath
 */
public class PersonResult {
    private String examRoll;
    private String position;
    
    public PersonResult(String examRoll, String position){
        setExamRoll(examRoll);
        setPosition(position);
    }
    
    private void setExamRoll(String examRoll){
        this.examRoll=examRoll;
    }
    
    private void setPosition(String position){
        this.position=position;
    }
    
    public String getExamRoll(){
        return this.examRoll;
    }
    
    public String getPosition(){
        return this.position;
    }
    
    public String toString(){
        return String.format("%s %s", getExamRoll(), getPosition());
    }
}
