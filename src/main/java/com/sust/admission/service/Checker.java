/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sust.admission.service;

/**
 *
 * @author Biswajit Debnath
 */
public class Checker {

    /**
     *
     * @param digitString
     * @return This method is used for checking a string which contains only
     * number It's return true or false only
     */
    public static boolean isNumber(String digitString) {

        if (digitString == null || digitString.length() == 0) {
            return false;
        }

        for (int i = 0; i < digitString.length(); i++) {
            if (!Character.isDigit(digitString.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidUnitName(String unitName) {

        if (unitName == null) {
            return false;
        }
        unitName = unitName.replace(" ", "");
        if (unitName.equals("")) {
            return false;
        }
        unitName = unitName.toUpperCase();
        for (int i = 0; i < unitName.length(); i++) {
            if (unitName.charAt(i) < 'A' || unitName.charAt(i) > 'Z') {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidGroupName(String groupName) {
        if (groupName != null) {
            groupName = groupName.replace(" ", "");
            if (!groupName.equals("")) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }

    }

    public static String correctMobileNumber(String mobileNumber) {
        
        if (mobileNumber.charAt(0) == '0') {
            mobileNumber = "88" + mobileNumber;
        }
        
        return mobileNumber;
    }

}
