/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sust.admission.textfilegeneration;

import com.sust.admission.resultdataprocess.PersonToInterview;
import com.sust.admission.resultdataprocess.TopSheetRawData;
import com.sust.admission.service.Checker;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Biswajit Debnath
 */
public class TextFileService {

    public static void mobileNumberTextFile(ArrayList<TopSheetRawData> topSheetRawDataList,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        Collections.sort(topSheetRawDataList);

        System.out.println("MOBILE NUMBER RAW DATA SHORTED");

        response.setContentType("application/csv");
        response.setHeader("Content-Disposition", "attachment;filename=MobileNumber.csv");

        PrintWriter out = response.getWriter();
        boolean DATANOTFOUND = true;
        try {
            System.out.println("MOBILE NUMBER GENERATION STARTED!");

            for (TopSheetRawData topSheetRawData : topSheetRawDataList) {
                ArrayList<PersonToInterview> topSheet = topSheetRawData.getTopSheet();
                System.out.println(topSheetRawData.getMeritListName());
                for (PersonToInterview personToInterview : topSheet) {
                    String mobileNumber = personToInterview.getContactNo();

                    mobileNumber = Checker.correctMobileNumber(mobileNumber);
                    System.out.println(mobileNumber);

                    if (mobileNumber != null) {
                        DATANOTFOUND = false;
                        out.println(mobileNumber);
                        // String examRoll = personToInterview.getExamRoll();
                        //  out.println(examRoll+" "+mobileNumber);
                    }
                }
            }
            System.out.println("MOBILE NUMBER GENERATION FINISHED!");
            if(DATANOTFOUND){
                out.println("NO MOBILE NUMBER FOUND!");
            }

        } finally {
            out.close();
        }
    }

}
