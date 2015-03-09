/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sust.admission.pdfgeneration;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.sust.admission.pdfgeneration.GenerateBytePdf;
import com.sust.admission.pdfgeneration.GenerateResPdf;
import com.sust.admission.resultdataprocess.PdfData;
import com.sust.admission.resultdataprocess.PersonToInterview;
import com.sust.admission.resultdataprocess.ProcessResultRawDataList;
import com.sust.admission.resultdataprocess.ProcessTopSheetRawDataList;
import com.sust.admission.resultdataprocess.ResultRawData;
import com.sust.admission.resultdataprocess.TopSheetRawData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Biswajit Debnath
 */
public class PdfService {

    public static void resultPdfService(
            ArrayList<ResultRawData> resultRawDataList,
            HttpServletRequest request,
            HttpServletResponse response) throws DocumentException, BadElementException, IOException, SQLException, ServletException {
        
        Collections.sort(resultRawDataList);
        ProcessResultRawDataList processResultRawDataList = new ProcessResultRawDataList(resultRawDataList);
        ArrayList<PdfData> pdfDataList = processResultRawDataList.getResultRawDataToPdfDataList();
        generateResultPdf(pdfDataList, request, response);
    }

    public static void topSheetPdfService(
            ArrayList<TopSheetRawData> topSheetRawDataList,
            HttpServletRequest request,
            HttpServletResponse response) throws DocumentException, IOException, SQLException, ServletException {
        
        Collections.sort(topSheetRawDataList);
        System.out.println("RAW DATA SHORTED");
        ProcessTopSheetRawDataList processTopSheetRawDataList = new ProcessTopSheetRawDataList(topSheetRawDataList);
        System.out.println("TOP SHEET RAW DATA PROCESSING COMPLETE!");
        ArrayList<PdfData> pdfDataList = processTopSheetRawDataList.getTopSheetRawDataToPdfDataList();
        System.out.println("PDF DATA LIST MAKING COMPLETE!");
        generateTopPdf(pdfDataList, request, response);
        System.out.println("PDF GENERATED!");
    
    }

    private static void generateResultPdf(ArrayList<PdfData> pdfDataList, HttpServletRequest request,
            HttpServletResponse response) throws IOException, SQLException, DocumentException, ServletException {

        Set<Integer> totalPdfNumber = new HashSet<Integer>();

        for (PdfData pdfData : pdfDataList) {
            totalPdfNumber.add(pdfData.getPdfNo());
        }

        int pdfCounter = 0;
        for (Integer pdfNo : totalPdfNumber) {

            ArrayList<PdfData> singlePdfData = new ArrayList<PdfData>();

            for (PdfData pdfData : pdfDataList) {
                if (pdfNo == pdfData.getPdfNo()) {
                    singlePdfData.add(pdfData);
                }
            }
            String pdfName = pdfNo + ".pdf";
            System.out.println("Pdf Name: " + pdfName);
            GenerateResPdf generatePdf = new GenerateResPdf(pdfDataList, pdfName, request, response);
            pdfCounter++;
        }

        System.out.println("Total Pdf Generated: " + pdfCounter);

        if (pdfCounter == 0) {
            RequestDispatcher reqDispatch;
            reqDispatch = request.getRequestDispatcher("/message.jsp?message=NOT HAVE ENOUGH DATA TO MAKE PDF!");
            reqDispatch.forward(request, response);
        }
    }
    
    private static void generateTopPdf(ArrayList<PdfData> pdfDataList, HttpServletRequest request,
            HttpServletResponse response) throws IOException, SQLException, DocumentException, ServletException {

        Set<Integer> totalPdfNumber = new HashSet<Integer>();

        for (PdfData pdfData : pdfDataList) {
            totalPdfNumber.add(pdfData.getPdfNo());
        }

        int pdfCounter = 0;
        for (Integer pdfNo : totalPdfNumber) {

            ArrayList<PdfData> singlePdfData = new ArrayList<PdfData>();

            for (PdfData pdfData : pdfDataList) {
                if (pdfNo == pdfData.getPdfNo()) {
                    singlePdfData.add(pdfData);
                }
            }

            String pdfName = pdfNo + ".pdf";
            System.out.println("Pdf Name" + pdfName);
            GenerateTopPdf generatePdf = new GenerateTopPdf(pdfDataList, pdfName, request, response);
            pdfCounter++;
        }

        System.out.println("Total Pdf Generated: " + pdfCounter);

        if (pdfCounter == 0) {
            RequestDispatcher reqDispatch;
            reqDispatch = request.getRequestDispatcher("/message.jsp?message=NOT HAVE ENOUGH DATA TO MAKE PDF!");
            reqDispatch.forward(request, response);
        }
    }
}
