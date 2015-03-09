/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sust.admission.resultdataprocess;

import com.itextpdf.text.pdf.PdfPTable;
import java.util.ArrayList;

/**
 *
 * @author Biswajit Debnath
 */
public class PdfData {
    
    private ArrayList<PdfPTable> pdfTableList;
    private String meritTitle;
    private int pdfNo;
    
    public PdfData(ArrayList<PdfPTable> pdfTableList , String meritTitle, int pdfNo){
        setPdfTableList(pdfTableList);
        setPdfNo(pdfNo);
        setMeritTitle(meritTitle);
    }
    
    private void setPdfTableList(ArrayList<PdfPTable> pdfTableList){
        this.pdfTableList = pdfTableList;
    }
    
    private void setMeritTitle(String meritTitle){
        this.meritTitle=meritTitle.toUpperCase();
    }
    
    private void setPdfNo(int pdfNo){
        this.pdfNo=pdfNo;
    }
    
    public ArrayList<PdfPTable> getPdfTableList(){
        return this.pdfTableList;
    }
    
    public String getMeritTitle(){
        return this.meritTitle;
    }
    
    public int getPdfNo(){
        return this.pdfNo;
    }
    
    public String toString(){
        return String.format("Pdf No: %d , Merit Title: %s\n Pdf Table List: %s", getPdfNo(),getMeritTitle(),getPdfTableList());
    }
}
