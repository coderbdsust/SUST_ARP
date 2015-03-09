/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sust.admission.servlet;

import com.sust.admission.service.AdminMethod;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Biswajit Debnath
 */
@WebServlet(name = "ClientDataServlet", urlPatterns = {"/adminDataServlet"})
public class AdminDataServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        System.out.println("ADMINDATASERVLET RESPONSING FOR WEBHIT!");

        boolean containsKey = request.getParameterMap().containsKey("methodName");
        printRequestParameters(request);

        if (containsKey) {

            String methodName = request.getParameter("methodName");
            System.out.println("METHOD NAME: " + methodName);
            if (methodName != null) {

                if (methodName.equals("unitGroup")) {
                    try {
                        AdminMethod.editUnitGroup(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(PdfServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception error) {
                        System.out.println("Exception Found In Edit Unit Group");
                    }

                } else if (methodName.equals("positionType")) {

                    try {
                        AdminMethod.editPositionType(request, response);
                    } catch (Exception error) {
                        System.out.println("Exception Found In Edit Position Type");
                    }

                } else if (methodName.equals("quota")) {

                    try {
                        AdminMethod.editQuota(request, response);
                    } catch (Exception error) {
                        System.out.println("Exception Found In Edit Quota");
                    }
                }
            }
        } else {
            System.out.println("NOT FOUND ANY METHOD NAME!!");
        }
    }

    private void printRequestParameters(HttpServletRequest request) {

        Enumeration params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String paramName = (String) params.nextElement();
            System.out.println("Attribute Name: " + paramName + ", Value: " + request.getParameter(paramName));
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
