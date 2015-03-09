/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sust.admission.resultdataprocess;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.sust.admission.pdfgeneration.LetterFont;
import java.util.ArrayList;

/**
 *
 * @author Biswajit Debnath
 */
public class ConvertTopSheetRawDataToPdfData {

    private ArrayList<PdfPTable> pdfTableList;
    private final TopSheetRawData topSheetRawData;
    private final LetterFont letterFont;
    private String meritTitle;
    private int pdfNo;

    public ConvertTopSheetRawDataToPdfData(TopSheetRawData topSheetRawData) throws DocumentException {
        this.topSheetRawData = topSheetRawData;
        this.pdfTableList = new ArrayList<PdfPTable>();
        letterFont = new LetterFont();
        setMeritTitle();
        setPdfNo(pdfNo);
        processTopSheetRawData();
    }

    public PdfData getPdfData() {
        return new PdfData(pdfTableList, meritTitle, pdfNo);
    }

    private void setMeritTitle() {
        String meritListName = topSheetRawData.getMeritListName();
        String quotaName = topSheetRawData.getQuotaName();
        String posType = topSheetRawData.getPosType();
        String meritTitle = meritListName + " - " + quotaName + " - " + posType;
        this.meritTitle = meritTitle;
    }

    private void setPdfNo(int pdfNo) {
        this.pdfNo = topSheetRawData.getPdfNo();
    }

    private void processTopSheetRawData() throws DocumentException {
        ArrayList<PersonToInterview> personToInterviewList = topSheetRawData.getTopSheet();
        int totalStudentRecord = personToInterviewList.size();
        System.out.println("Raw Result Found:" + totalStudentRecord);
        int perpageStudentNumber = 35;
        int tableCounter = totalStudentRecord / perpageStudentNumber + 1;
        int sublistSlider = 0;

        while (totalStudentRecord != 0 && sublistSlider < tableCounter) {

            int lowerIndex = sublistSlider * perpageStudentNumber;
            int upperIndex = lowerIndex + perpageStudentNumber;

            if (upperIndex > totalStudentRecord) {
                upperIndex = totalStudentRecord;
            }

            ArrayList<PersonToInterview> perPagePersonToInterviewList = new ArrayList<PersonToInterview>(
                    personToInterviewList.subList(lowerIndex, upperIndex)
            );

            PdfPTable dataTable = getFiveColumnDataTable(perPagePersonToInterviewList);

            pdfTableList.add(dataTable);
            sublistSlider++;
        }
    }

    private PdfPTable getFiveColumnDataTable(ArrayList<PersonToInterview> perpagePersonToInterviewList) throws DocumentException {
        PdfPTable dataTable = new PdfPTable(6);
        float[] tableColumnWidth = {1.8f, 2.2f, 2.6f, 9f, 1.8f, 3.5f};
        dataTable.setWidthPercentage(92);
        dataTable.setSpacingBefore(32);
        dataTable.setSpacingAfter(50);
        dataTable.setWidths(tableColumnWidth);
        int totalRowInPage = 36;
        int totalRowPerPageForStudent = totalRowInPage - 1;
        int totalColumnForStudent = 4;
        int nodeColumnCounter = 0;
        int resultAddedToPdfTableCounter = 0;

        PdfPCell serialCell = new PdfPCell(new Paragraph("SERIAL", letterFont.getFont("TIMES_ROMAN", 9, "BOLD")));
        PdfPCell positionCell = new PdfPCell(new Paragraph("POSITION", letterFont.getFont("TIMES_ROMAN", 9, "BOLD")));
        PdfPCell examRollCell = new PdfPCell(new Paragraph("EXAM ROLL", letterFont.getFont("TIMES_ROMAN", 9, "BOLD")));
        PdfPCell nameCell = new PdfPCell(new Paragraph("NAME", letterFont.getFont("TIMES_ROMAN", 9, "BOLD")));
        PdfPCell deptCell = new PdfPCell(new Paragraph("DEPT", letterFont.getFont("TIMES_ROMAN", 9, "BOLD")));
        PdfPCell signatureCell = new PdfPCell(new Paragraph("SIGNATURE", letterFont.getFont("TIMES_ROMAN", 9, "BOLD")));
        PdfPCell blankCell = new PdfPCell(new Paragraph(""));

        serialCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        positionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        examRollCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        deptCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        signatureCell.setHorizontalAlignment(Element.ALIGN_CENTER);

        dataTable.addCell(serialCell);
        dataTable.addCell(positionCell);
        dataTable.addCell(examRollCell);
        dataTable.addCell(nameCell);
        dataTable.addCell(deptCell);
        dataTable.addCell(signatureCell);

        for (PersonToInterview perToIn : perpagePersonToInterviewList) {
            
            serialCell = new PdfPCell(new Paragraph(perToIn.getSerial(), letterFont.getFont("TIMES_ROMAN", 12, "NORMAL")));
            serialCell.setPaddingBottom(4);
            positionCell = new PdfPCell(new Paragraph(perToIn.getPosition(), letterFont.getFont("TIMES_ROMAN", 12, "NORMAL")));
            examRollCell = new PdfPCell(new Paragraph(perToIn.getExamRoll(), letterFont.getFont("TIMES_ROMAN", 12, "NORMAL")));
            serialCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            positionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            examRollCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            dataTable.addCell(serialCell);
            dataTable.addCell(positionCell);
            dataTable.addCell(examRollCell);
            dataTable.addCell(blankCell);
            dataTable.addCell(blankCell);
            dataTable.addCell(blankCell);
        }
        return dataTable;
    }
}
