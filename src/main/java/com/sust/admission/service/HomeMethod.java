/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sust.admission.service;

import com.sust.admission.databaseservice.DatabaseService;
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
import javax.servlet.http.HttpSession;
import jdk.nashorn.internal.runtime.DebugLogger;

/**
 *
 * @author Biswajit Debnath
 */
public class HomeMethod {

    private static RequestDispatcher requestDispatcher;
    private static String DEFAULTUSER = "admin.sust";
    private static String DEFAULTPASS = "sust";

    public static void indexPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageSelected = request.getParameter("choiceButton");
        if (pageSelected.equals("Login")) {

            requestDispatcher = request.getRequestDispatcher("/login.html");
            requestDispatcher.forward(request, response);

        } else if (pageSelected.equals("View Only")) {
            requestDispatcher = request.getRequestDispatcher("/viewonly.html");
            requestDispatcher.forward(request, response);
        }
    }

    public static void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username.equals(DEFAULTUSER) && password.equals(DEFAULTPASS)) {
            requestDispatcher = request.getRequestDispatcher("/menu.html");
            requestDispatcher.forward(request, response);
        } else {
            
            request.setAttribute("MESSAGE","INVALID USER/PASSWORD");
            requestDispatcher = request.getRequestDispatcher("/login.html");
            requestDispatcher.forward(request, response);

        }
    }

    public static void menuPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageSelected = request.getParameter("choiceButton");

        if (pageSelected.equals("Admin")) {
            requestDispatcher = request.getRequestDispatcher("/admin.html");
            requestDispatcher.forward(request, response);

        } else if (pageSelected.equals("Generation")) {

            List list = RQPdfMethod.listResultQuery();
            request.setAttribute("listResultQuery", list);
            requestDispatcher = request.getRequestDispatcher("/resquery.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    
}
