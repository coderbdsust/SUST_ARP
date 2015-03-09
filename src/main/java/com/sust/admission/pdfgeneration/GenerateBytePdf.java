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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Biswajit Debnath
 */
public class GenerateBytePdf {

    private PdfHeader pdfHeader;
    private ArrayList<PdfData> pdfDataList;
  

    public GenerateBytePdf(ArrayList<PdfData> pdfDataList) throws BadElementException, IOException, SQLException, DocumentException {
        initializePdfHeader();
       
        setPdfDataList(pdfDataList);
    }

 

    private void initializePdfHeader() throws BadElementException, IOException {
        pdfHeader = new PdfHeader();
    }

    private void setPdfDataList(ArrayList<PdfData> pdfDataList) {
        this.pdfDataList = pdfDataList;
    }

    public byte[] getBytePdf() throws SQLException, DocumentException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Document document = new Document();
        document.setPageSize(PageSize.A4);

        try {

            PdfWriter.getInstance(document, byteArrayOutputStream);
            System.out.println("Byte Pdf Single Pdf Generation Started");
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

            System.out.println("Byte Single Pdf Generation Completed");
        } catch (Exception e) {
            System.out.println("PDF GENERATION NOT POSSIBLE!");
        }
        byte[] byteContent = byteArrayOutputStream.toByteArray();
        return byteContent;
    }
}
