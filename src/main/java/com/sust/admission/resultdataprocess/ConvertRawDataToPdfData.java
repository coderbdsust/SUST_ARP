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
public class ConvertRawDataToPdfData {

    private ArrayList<PdfPTable> pdfTableList;
    private final ResultRawData resultRawData;
    private final LetterFont letterFont;
    private String meritTitle;
    private int pdfNo;
    private final int OUTOFBOUND = -1;

    public ConvertRawDataToPdfData(ResultRawData resultRawData) throws DocumentException {
        this.resultRawData = resultRawData;
        this.pdfTableList = new ArrayList<PdfPTable>();
        letterFont = new LetterFont();
        setMeritTitle();
        setPdfNo(pdfNo);
        processResultRawData();
    }

    public PdfData getPdfData() {
        return new PdfData(pdfTableList, meritTitle, pdfNo);
    }

    private void setMeritTitle() {
        String meritListName = resultRawData.getMeritListName();
        String quotaName = resultRawData.getQuotaName();
        String posType = resultRawData.getPosType();
        String meritTitle = meritListName + " - " + quotaName + " - " + posType + " LIST";
        this.meritTitle = meritTitle;
    }

    private void setPdfNo(int pdfNo) {
        this.pdfNo = resultRawData.getPdfNo();
    }

    private void processResultRawData() throws DocumentException {
        ArrayList<PersonResult> meritResultList = resultRawData.getResultList();
        System.out.println("Processing MeritList: " + resultRawData.getMeritListName() + " " + resultRawData.getQuotaName() + " " + resultRawData.getPosType());
        int totalStudentRecord = meritResultList.size();
        System.out.println("Raw Result Found:" + totalStudentRecord);
        int perpageStudentNumber = 140;
        int tableCounter = totalStudentRecord / perpageStudentNumber + 1;
        int sublistSlider = 0;

        while (totalStudentRecord != 0 && sublistSlider < tableCounter) {

            int lowerIndex = perpageStudentNumber * sublistSlider;
            int upperIndex = perpageStudentNumber * sublistSlider + perpageStudentNumber;

            if (upperIndex > totalStudentRecord) {
                upperIndex = totalStudentRecord;
            }

            ArrayList<PersonResult> perpagePersonResult = new ArrayList<PersonResult>(
                    meritResultList.subList(lowerIndex, upperIndex)
            );

            int studentResultFoundPerPage = perpagePersonResult.size();

            PdfPTable dataTable = null;

            if (studentResultFoundPerPage != 0) {
                System.out.println("Student Found Per Page: " + studentResultFoundPerPage);
                if (studentResultFoundPerPage <= 35) {
                    dataTable = getOneColumnDataTable(perpagePersonResult);
                } else if (studentResultFoundPerPage <= 140) {
                    dataTable = getFourColumnDataTable(perpagePersonResult);
                }
                pdfTableList.add(dataTable);
            } else {
                System.out.println("Student Found Per Page: " + studentResultFoundPerPage);
                break;
            }

            sublistSlider++;
        }
    }

    private PdfPTable getFourColumnDataTable(ArrayList<PersonResult> perPagePersonResult) throws DocumentException {
        PdfPTable dataTable = new PdfPTable(11);
        float[] tableColumnWidth = {3.2f, 3.2f, 1f, 3.2f, 3.2f, 1f, 3.2f, 3.2f, 1f, 3.2f, 3.2f};
        dataTable.setWidthPercentage(92);
        dataTable.setSpacingBefore(32);
        dataTable.setSpacingAfter(50);
        dataTable.setWidths(tableColumnWidth);

        int totalRowInPage = 36;
        int totalRowPerPageForStudent = totalRowInPage - 1;
        int totalColumnForStudent = 4;
        int nodeColumnCounter = 0;
        int resultAddedToPdfTableCounter = 0;

        PdfPCell examRollCell = new PdfPCell(new Paragraph("EXAM ROLL", letterFont.getFont("TIMES_ROMAN", 8, "BOLD")));
        PdfPCell positionCell = new PdfPCell(new Paragraph("POSITION", letterFont.getFont("TIMES_ROMAN", 8, "BOLD")));
        PdfPCell blankCell = new PdfPCell(new Paragraph(""));

        examRollCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        positionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        blankCell.setBorder(Rectangle.NO_BORDER);

        examRollCell.setPaddingBottom(5);
        positionCell.setPaddingBottom(5);

        for (int rowIndex = 0; rowIndex < totalRowInPage; rowIndex++) {

            for (int nodeColumn = 0; nodeColumn < totalColumnForStudent; nodeColumn++) {

                int validCellForStudent = rowIndex - 1;
                int studentIndex = totalRowPerPageForStudent * nodeColumn + validCellForStudent;
                System.out.printf("%3d", studentIndex);
                if (studentIndex >= perPagePersonResult.size()) {
                    studentIndex = OUTOFBOUND;
                }
                System.out.printf("(%3d) ", studentIndex);

                if (rowIndex == 0) {

                    int columnDataIndex = (nodeColumn) * totalRowPerPageForStudent;

                    if (columnDataIndex >= perPagePersonResult.size()) {
                        columnDataIndex = OUTOFBOUND;
                    }

                    if (columnDataIndex != OUTOFBOUND) {

                        if (nodeColumn != 3) {
                            dataTable.addCell(examRollCell);
                            dataTable.addCell(positionCell);
                            dataTable.addCell(blankCell);
                        } else {
                            dataTable.addCell(examRollCell);
                            dataTable.addCell(positionCell);
                        }

                    } else {
                        if (nodeColumn != 3) {
                            dataTable.addCell(blankCell);
                            dataTable.addCell(blankCell);
                            dataTable.addCell(blankCell);
                        } else {
                            dataTable.addCell(blankCell);
                            dataTable.addCell(blankCell);
                        }
                    }
                    continue;
                }

                if (studentIndex != OUTOFBOUND) {
                    resultAddedToPdfTableCounter++;

                    PersonResult personResult = perPagePersonResult.get(studentIndex);

                    examRollCell = new PdfPCell(new Paragraph(personResult.getExamRoll(), letterFont.getFont("TIMES_ROMAN", 11, "NORMAL")));
                    positionCell = new PdfPCell(new Paragraph(personResult.getPosition(), letterFont.getFont("TIMES_ROMAN", 11, "NORMAL")));

                    examRollCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    positionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    examRollCell.setPaddingBottom(5);
                    positionCell.setPaddingBottom(5);
                    dataTable.addCell(examRollCell);
                    dataTable.addCell(positionCell);
                    nodeColumnCounter++;

                } else {
                    dataTable.addCell(blankCell);
                    dataTable.addCell(blankCell);
                    nodeColumnCounter++;

                }

                if (nodeColumnCounter < 4) {
                    dataTable.addCell(blankCell);
                } else {
                    nodeColumnCounter = 0;
                }
            }
            System.out.println("");
        }
        return dataTable;
    }

    private PdfPTable getOneColumnDataTable(ArrayList<PersonResult> perPagePersonResult) throws DocumentException {
        PdfPTable dataTable = new PdfPTable(8);
        float[] tableColumnWidth = {2.5f, 2.5f, 1f, 4f, 4f, 1f, 2.5f, 2.5f};
        dataTable.setWidthPercentage(92);
        dataTable.setSpacingBefore(32);
        dataTable.setSpacingAfter(32);
        dataTable.setWidths(tableColumnWidth);

        int totalRowInPage = 36;
        int totalRowPerPageForStudent = totalRowInPage - 1;
        int totalNodeColumnForStudent = 3;
        int nodeColumnCounter = 0;
        int resultAddedToPdfTableCounter = 0;

        PdfPCell examRollCell = new PdfPCell(new Paragraph("EXAM ROLL", letterFont.getFont("TIMES_ROMAN", 8, "BOLD")));
        PdfPCell positionCell = new PdfPCell(new Paragraph("POSITION", letterFont.getFont("TIMES_ROMAN", 8, "BOLD")));
        PdfPCell blankCell = new PdfPCell(new Paragraph(""));

        examRollCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        positionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        blankCell.setBorder(Rectangle.NO_BORDER);

        examRollCell.setPaddingBottom(5);
        positionCell.setPaddingBottom(5);

        for (int rowIndex = 0; rowIndex < totalRowInPage; rowIndex++) {

            for (int nodeColumn = 0; nodeColumn < totalNodeColumnForStudent; nodeColumn++) {

                if (nodeColumn == 0) {
                    dataTable.addCell(blankCell);
                    dataTable.addCell(blankCell);
                    dataTable.addCell(blankCell);
                    continue;
                }

                if (nodeColumn == 2) {
                    dataTable.addCell(blankCell);
                    dataTable.addCell(blankCell);
                    continue;
                }

                if (rowIndex == 0) {
                    dataTable.addCell(examRollCell);
                    dataTable.addCell(positionCell);
                    dataTable.addCell(blankCell);
                    continue;
                }

                int studentIndex = rowIndex - 1;

                if (studentIndex >= perPagePersonResult.size()) {
                    studentIndex = OUTOFBOUND;
                }

                if (studentIndex != OUTOFBOUND) {

                    PersonResult personResult = perPagePersonResult.get(studentIndex);

                    examRollCell = new PdfPCell(new Paragraph(personResult.getExamRoll(), letterFont.getFont("TIMES_ROMAN", 11, "NORMAL")));
                    positionCell = new PdfPCell(new Paragraph(personResult.getPosition(), letterFont.getFont("TIMES_ROMAN", 11, "NORMAL")));

                    examRollCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    positionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    examRollCell.setPaddingBottom(5);
                    positionCell.setPaddingBottom(5);

                    dataTable.addCell(examRollCell);
                    dataTable.addCell(positionCell);
                    dataTable.addCell(blankCell);

                } else {
                    dataTable.addCell(blankCell);
                    dataTable.addCell(blankCell);
                    dataTable.addCell(blankCell);
                }

            }

        }

        return dataTable;
    }

}
