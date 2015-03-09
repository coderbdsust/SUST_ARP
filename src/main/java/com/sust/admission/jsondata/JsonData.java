/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sust.admission.jsondata;

import com.sust.admission.constant.DbUser;
import com.sust.admission.databaseservice.DatabaseService;
import com.sust.admission.databaseservice.GetQuery;
import com.sust.admission.jsondata.JsonConverter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Biswajit Debnath
 */
public class JsonData {

    private static RequestDispatcher reqDispatch;

    private static final DatabaseService dbService = new DatabaseService(
            DbUser.DATABASETYPE,
            DbUser.DATABASEURL,
            DbUser.USERNAME,
            DbUser.PASSWORD
    );

    public static void getQuotaJson(HttpServletRequest request, HttpServletResponse response) {
        String query = GetQuery.getQuota();
        ResultSet result = dbService.getResultSet(query);
        JSONArray jsonArray = JsonConverter.convertQuota(result);
        sendJsonData(request, response, jsonArray, "Quota Json Data");
    }

    public static void getUnitGroupJson(HttpServletRequest request, HttpServletResponse response) {
        String query = GetQuery.getUnitGroup();
        ResultSet result = dbService.getResultSet(query);
        JSONArray jsonArray = JsonConverter.convertUnitGroup(result);
        sendJsonData(request, response, jsonArray, "Unit:Group Json Data");
    }

    public static void getPositionTypeJson(HttpServletRequest request, HttpServletResponse response) {
        String query = GetQuery.getPosType();
        ResultSet result = dbService.getResultSet(query);
        JSONArray jsonArray = JsonConverter.convertPositionType(result);

        sendJsonData(request, response, jsonArray, "PositionType Json Data");
    }

    private static void sendJsonData(HttpServletRequest request, HttpServletResponse response, JSONArray jsonArray, String jsonLabel) {
        response.setContentType("application/json");
        System.out.println(jsonArray);
        try {
            PrintWriter out = response.getWriter();
            out.print(jsonArray);
            out.flush();
            System.out.println(jsonLabel + " Send From Server");
        } catch (IOException io) {
            io.printStackTrace();
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}
