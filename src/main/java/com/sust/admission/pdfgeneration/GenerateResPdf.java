/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sust.admission.pdfgeneration;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sust.admission.resultdataprocess.PdfData;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Biswajit Debnath
 */
public class GenerateResPdf {

    private PdfHeader pdfHeader;
    private ArrayList<PdfData> pdfDataList;
    private String pdfName;

    public GenerateResPdf(
            ArrayList<PdfData> pdfDataList, String pdfName,
            HttpServletRequest request,
            HttpServletResponse response) throws BadElementException, IOException, SQLException, DocumentException, ServletException {
        initializePdfHeader();
        setPdfName(pdfName);
        setPdfDataList(pdfDataList);
        generatePdf(request, response);
    }

    private void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }

    private void initializePdfHeader() throws BadElementException, IOException {
        pdfHeader = new PdfHeader();
    }

    private void setPdfDataList(ArrayList<PdfData> pdfDataList) {
        this.pdfDataList = pdfDataList;
    }

    public void generatePdf(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws SQLException, DocumentException, ServletException, IOException {
        
        Document document = new Document();
        document.setPageSize(PageSize.A4);
        
        try {

            response.setContentType("application/pdf");
//            response.setHeader("Content-disposition", "attachment; filename=\"" + pdfName);
            PdfWriter.getInstance(document, response.getOutputStream());

            System.out.println("Single Pdf Generation Started");
            document.open();

            for (PdfData pdfdata : pdfDataList) {

                ArrayList<PdfPTable> pdfTableList = pdfdata.getPdfTableList();
                String meritTitle = pdfdata.getMeritTitle();

                for (PdfPTable perPageTable : pdfTableList) {
                    
                  
                    Image image = pdfHeader.getSustLogo();
                    Paragraph pdfTitleParagraph = pdfHeader.getUniversityName();
                    Paragraph pdfSubTitleParagraph = pdfHeader.getAdmissionName();
                    Paragraph meritTitleParagraph = pdfHeader.getMeritTitle(meritTitle);

                    document.add(image);
                    document.add(pdfTitleParagraph);
                    document.add(pdfSubTitleParagraph);
                    document.add(meritTitleParagraph);
                    document.add(perPageTable);
                    document.newPage();
                 
                }
            }

            document.close();

            System.out.println(" Single Pdf Generation Completed");
        } catch (Exception e) {
            System.out.println("PDF GENERATION EXCEPTION OCCUR!");
        }
    }
}
