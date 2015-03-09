/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sust.admission.service;

import com.itextpdf.text.DocumentException;
import com.sust.admission.constant.DbUser;
import com.sust.admission.databaseservice.DatabaseService;
import com.sust.admission.databaseservice.GetQuery;
import com.sust.admission.debug.Debugger;
import com.sust.admission.pdfgeneration.PdfService;
import com.sust.admission.resultdataprocess.PersonResult;
import com.sust.admission.resultdataprocess.PersonToInterview;
import com.sust.admission.resultdataprocess.ResultRawData;
import com.sust.admission.resultdataprocess.TopSheetRawData;
import com.sust.admission.textfilegeneration.TextFileService;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Biswajit Debnath
 */
public class RQPdfMethod {

    private static RequestDispatcher reqDispatch;

    private static final DatabaseService dbService = new DatabaseService(
            DbUser.DATABASETYPE,
            DbUser.DATABASEURL,
            DbUser.USERNAME,
            DbUser.PASSWORD
    );

    private static List list = new ArrayList();
    private static List topSheetList = new ArrayList();
    private static ResultSet resultSet;
    private static Debugger log = new Debugger(true);

    public static void editResultQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, DocumentException {

        String operation = request.getParameter("operation");

        String[] sPos = request.getParameterValues("startPosition");
        String[] ePos = request.getParameterValues("endPosition");
        String[] pdfNo = request.getParameterValues("pdfNo");
        String[] priority = request.getParameterValues("priority");
        String[] keys = request.getParameterValues("queryKey");

        int startPosition = 0;
        int endPosition = 0;
        int pdf = 0;
        int prior = 0;
        int key = 0;

        for (int index = 0; index < keys.length; index++) {

            try {

                if (!(sPos[index] == null || sPos[index].equals(""))) {
                    startPosition = Integer.parseInt(sPos[index]);
                } else {
                    startPosition = 0;
                }

                if (!(ePos[index] == null || ePos[index].equals(""))) {
                    endPosition = Integer.parseInt(ePos[index]);
                } else {
                    endPosition = 0;
                }

                if (!(pdfNo[index] == null || pdfNo[index].equals(""))) {
                    pdf = Integer.parseInt(pdfNo[index]);
                } else {
                    pdf = 0;
                }

                if (!(priority[index] == null || priority[index].equals(""))) {
                    prior = Integer.parseInt(priority[index]);
                } else {
                    prior = 0;
                }

                if (!(keys[index] == null || keys[index].equals(""))) {
                    key = Integer.parseInt(keys[index]);
                } else {
                    key = 0;
                }

                if (pdf > 0 && prior > 0) {

                    if (startPosition > 0 && endPosition > 0) {
                        String query = GetQuery.updateResultQueryWithPosition(startPosition, endPosition, pdf, prior, key);
                        System.out.println("Query: " + query);
                        dbService.queryExcute(query);
                    } else {
                        String query = GetQuery.updateResultQuery(pdf, prior, key);
                        System.out.println("Query: " + query);
                        dbService.queryExcute(query);
                    }
                } else {
                    String query = GetQuery.updateNullResultQuery(key);
                    System.out.println("Query: " + query);
                    dbService.queryExcute(query);
                }

            } catch (Exception error) {
                System.out.println(error);
            }
        }

        if (operation.equals("result pdf")) {
            ArrayList<ResultRawData> resultRawDataList = getResultRawDataList();
            PdfService.resultPdfService(resultRawDataList, request, response);
        } else if (operation.equals("topsheet pdf")) {
            ArrayList<TopSheetRawData> topSheetRawDataList = getTopSheetRawDataList();
            PdfService.topSheetPdfService(topSheetRawDataList, request, response);
        
        }else if(operation.equals("mobile number")){
            ArrayList<TopSheetRawData> topSheetRawDataList = getTopSheetRawDataList();
            TextFileService.mobileNumberTextFile(topSheetRawDataList, request, response);
        }else{
            System.out.println("NO OPERATION FOUND!");
        }
    }

    public static List listResultQuery() {
        addNewMeritListInResultQuery();
        try {
            while (resultSet.next()) {
                list.add(resultSet.getString("query_key"));
                list.add(resultSet.getString("unit_name"));
                list.add(resultSet.getString("group_name"));
                list.add(resultSet.getString("postype_name"));
                list.add(resultSet.getString("quota_name"));
                list.add(resultSet.getString("start_position"));
                list.add(resultSet.getString("end_position"));
                list.add(resultSet.getString("pdf_no"));
                list.add(resultSet.getString("priority"));
            }
        } catch (SQLException error) {
            error.printStackTrace();
        } catch (NullPointerException error) {
            error.printStackTrace();
        } catch (Exception error) {
            error.printStackTrace();
        }
        return list;
    }

    private static void addNewMeritListInResultQuery() {
        list.clear();
        String query = GetQuery.addMeritList();
        System.out.println("COMBINING ADMIN DATA QUERY: " + query);
        dbService.queryExcute(query);
        query = GetQuery.getResultQuery();
        System.out.println("MAKING GENERATOR TABLE QUERY: " + query);
        resultSet = dbService.getResultSet(query);
    }

    public static ArrayList<ResultRawData> getResultRawDataList() throws SQLException {

        ArrayList<ResultRawData> resultRawDataList = new ArrayList<ResultRawData>();

        List pdfData = listResultQuery();
        Iterator itr;
        for (itr = pdfData.iterator(); itr.hasNext();) {

            String key = (String) itr.next();
            String unitName = (String) itr.next();
            String groupName = (String) itr.next();
            String posTypeName = (String) itr.next();
            String quotaName = (String) itr.next();
            String startPosition = (String) itr.next();
            String endPosition = (String) itr.next();
            String pdfNo = (String) itr.next();
            String priority = (String) itr.next();

            String meritList;

            if (!Checker.isNumber(groupName)) {
                meritList = "Unit: " + unitName + " (" + groupName + ")";
            } else {
                meritList = "Unit: " + unitName + " Group: " + groupName;
            }

            if (Checker.isNumber(pdfNo) && Checker.isNumber(priority)) {
                ResultRawData resultData;

                if (Checker.isNumber(startPosition) && Checker.isNumber(endPosition)) {

                    String query = GetQuery.getStudentResultInRange(meritList, posTypeName, quotaName, startPosition, endPosition);
                    System.out.println("RESULT QUERY IN RANGE: " + query);
                    ResultSet result = dbService.getResultSet(query);
                    ArrayList<PersonResult> meritResultList = getPersonResultList(result);
                    resultData = new ResultRawData(meritResultList, Integer.parseInt(pdfNo), Integer.parseInt(priority), posTypeName, quotaName, meritList);

                } else {

                    String query = GetQuery.getStudentResult(meritList, posTypeName, quotaName);
                    System.out.println("RESULT QUERY WITHOUT RANGE: " + query);
                    ResultSet result = dbService.getResultSet(query);
                    ArrayList<PersonResult> meritResultList = getPersonResultList(result);
                    resultData = new ResultRawData(meritResultList, Integer.parseInt(pdfNo), Integer.parseInt(priority), posTypeName, quotaName, meritList);
                }
                resultRawDataList.add(resultData);
            }
        }
        return resultRawDataList;
    }

    private static ArrayList<PersonResult> getPersonResultList(ResultSet result) throws SQLException {
        ArrayList<PersonResult> resultList = new ArrayList<PersonResult>();
        while (result.next()) {
            String examRoll = result.getString("exam_roll");
            String position = result.getString("position");
            resultList.add(new PersonResult(examRoll, position));
        }
        return resultList;
    }

    /*
     topSheet Start
     */
    private static ArrayList<PersonToInterview> getPersonToInterviewList(ResultSet topSheet) throws SQLException {
        ArrayList<PersonToInterview> topSheetList = new ArrayList<PersonToInterview>();
        while (topSheet.next()) {
            String serial = topSheet.getString("serial");
            String position = topSheet.getString("position");
            String examRoll = topSheet.getString("exam_roll");
            String contactNo = topSheet.getString("contact_no");
            topSheetList.add(new PersonToInterview(serial, position, examRoll, contactNo));
        }
        return topSheetList;
    }

    public static ArrayList<TopSheetRawData> getTopSheetRawDataList() throws SQLException {

        ArrayList<TopSheetRawData> topSheetRawDataList = new ArrayList<TopSheetRawData>();

        List pdfData = listResultQuery();
        Iterator itr;
        for (itr = pdfData.iterator(); itr.hasNext();) {

            String key = (String) itr.next();
            String unitName = (String) itr.next();
            String groupName = (String) itr.next();
            String posTypeName = (String) itr.next();
            String quotaName = (String) itr.next();
            String startPosition = (String) itr.next();
            String endPosition = (String) itr.next();
            String pdfNo = (String) itr.next();
            String priority = (String) itr.next();
            String meritList;
            System.out.println(":::::::::::::::::DEBUG LOG ::::::::::::::");
            log.debugLog("Key: ", key);
            log.debugLog("Unit Name: ", unitName);
            log.debugLog("Group No: ", groupName);
            log.debugLog("PosType: ", posTypeName);
            log.debugLog("Quota: ", quotaName);
            log.debugLog("Start Position: ", startPosition);
            log.debugLog("End Position: ", endPosition);
            log.debugLog("Pdf No :", pdfNo);
            log.debugLog("Priority : ", priority);

//            if (groupName.equals("0")) {
//                meritList = "Unit: " + unitName;
//            } else {
//                meritList = "Unit: " + unitName + " Group: " + groupName;
//            }
            if (!Checker.isNumber(groupName)) {
                meritList = "Unit: " + unitName + " (" + groupName + ")";
            } else {
                meritList = "Unit: " + unitName + " Group: " + groupName;
            }

            System.out.println("MERITLIST: " + meritList);
            if (Checker.isNumber(pdfNo) && Checker.isNumber(priority)) {

                if (Checker.isNumber(startPosition) && Checker.isNumber(endPosition)) {
                    TopSheetRawData topSheetData;
                    String query = GetQuery.getTopSheetInRange(meritList, posTypeName, quotaName, startPosition, endPosition);
                    System.out.println("TOPSHEET QUERY IN RANGE: " + query);
                    ResultSet topSheet = dbService.getResultSet(query);
                    ArrayList<PersonToInterview> topSheetList = getPersonToInterviewList(topSheet);
                    topSheetData = new TopSheetRawData(topSheetList, Integer.parseInt(pdfNo), Integer.parseInt(priority), posTypeName, quotaName, meritList);
                    topSheetRawDataList.add(topSheetData);
                } else {
                    TopSheetRawData topSheetData;
                    String query = GetQuery.getTopSheet(meritList, posTypeName, quotaName);
                    System.out.println("TOPSHEET QUERY IN RANGE: " + query);
                    ResultSet topSheet = dbService.getResultSet(query);
                    ArrayList<PersonToInterview> topSheetList = getPersonToInterviewList(topSheet);
                    topSheetData = new TopSheetRawData(topSheetList, Integer.parseInt(pdfNo), Integer.parseInt(priority), posTypeName, quotaName, meritList);
                    topSheetRawDataList.add(topSheetData);
                    System.out.println("CHECK POSITION ");
                }
            }
        }
        return topSheetRawDataList;
    }
    /*
     topSheet End
     */

}
