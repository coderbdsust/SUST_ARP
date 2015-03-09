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
import com.sust.admission.pdfgeneration.PdfService;
import com.sust.admission.resultdataprocess.PersonResult;
import com.sust.admission.resultdataprocess.ResultRawData;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class AdminMethod {

    private static RequestDispatcher reqDispatch;

    private static final DatabaseService dbService = new DatabaseService(
            DbUser.DATABASETYPE,
            DbUser.DATABASEURL,
            DbUser.USERNAME,
            DbUser.PASSWORD
    );

    private static List list = new ArrayList();
    private static ResultSet resultSet;

    public static void editUnitGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        System.out.println("EDIT UNIT GROUP FUNCTION ");
        String operation = request.getParameter("operation");
        System.out.println("OPERATION NAME: " + operation);

        boolean validUnitName = false;
        boolean validGroupNo = false;

        if (operation.equals("Add")) {
            
            if (request.getParameter("unitName") != null) {
                
                String unitName = request.getParameter("unitName").toUpperCase();
                String groupNo = request.getParameter("groupNo").toUpperCase();
                
                if (Checker.isValidUnitName(unitName)) {
                    validUnitName = true;
                }
                
                if (Checker.isValidGroupName(groupNo)) {
                    validGroupNo = true;
                }
                
                if (validUnitName == true && validGroupNo) {
                    
                    if(groupNo.equals("")){
                        groupNo = "0";
                    }
                    
                    String unitQuery = GetQuery.addUnit(unitName);
                    debugLog("Unit Query: " + unitQuery);
                    String groupQuery = GetQuery.addGroup(groupNo);
                    debugLog("Group Query: " + groupQuery);
                    String meritListNameQuery = GetQuery.addMeritListName(unitName, groupNo);
                    debugLog("Merit List Name Query: " + meritListNameQuery);
                    
                    dbService.queryExcute(unitQuery);
                    dbService.queryExcute(groupQuery);
                    dbService.queryExcute(meritListNameQuery);
                }
            }
        } else if (operation.equals("Delete")) {
            System.out.println("Delete");
            if (request.getParameter("checkBox") != null) {
                String[] keys = request.getParameterValues("checkBox");
                for (String key : keys) {
                    String query = GetQuery.delUnitGroup(key);
                    dbService.queryExcute(query);
                }
            }
        }

    }

    public static void editPositionType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("EDIT POSITION TYPE FUNCTION ");
        String operation = request.getParameter("operation");
        System.out.println("OPERATION NAME: " + operation);

        if (operation.equals("Add")) {
            String posType = request.getParameter("positionType").toUpperCase();
            if (!(posType == null || posType.equals(""))) {
                String query = GetQuery.addPosType(posType);
                dbService.queryExcute(query);
                debugLog(query);
            }

        } else if (operation.equals("Delete")) {
            if (request.getParameter("checkBox") != null) {
                String[] keys = request.getParameterValues("checkBox");
                String query;
                for (String key : keys) {
                    query = GetQuery.delPosType(key);
                    debugLog("Query " + query);
                    dbService.queryExcute(query);
                }
            }
        }

    }

    public static void editQuota(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("EDIT QUOTA FUNCTION ");
        String operation = request.getParameter("operation");
        System.out.println("OPERATION NAME: " + operation);
        if (operation.equals("Add")) {
            String quota = request.getParameter("quotaName").toUpperCase();
            if (!(quota == null || quota.equals(""))) {
                String query = GetQuery.addQuota(quota);
                dbService.queryExcute(query);
                debugLog(query);
            }
        } else if (operation.equals("Delete")) {
            if (request.getParameter("checkBox") != null) {
                String[] keys = request.getParameterValues("checkBox");
                String query;
                for (String key : keys) {
                    query = GetQuery.delQuota(key);
                    debugLog("Query " + query);
                    dbService.queryExcute(query);
                }
            }

        }
    }

    public static void debugLog(Object... values) {
        for (Object value : values) {
            System.out.print(value + " ");
        }
        System.out.println("");
    }
}
