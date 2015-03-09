/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sust.admission.resultdataprocess;

import com.itextpdf.text.DocumentException;
import java.util.ArrayList;

/**
 *
 * @author Biswajit Debnath
 */
public class ProcessTopSheetRawDataList {
    ArrayList<TopSheetRawData> topSheetRawDataList;
    
    public ProcessTopSheetRawDataList(ArrayList<TopSheetRawData> topSheetRawDataList){
        this.topSheetRawDataList=topSheetRawDataList;
    }
    
    public ArrayList<PdfData> getTopSheetRawDataToPdfDataList() throws DocumentException{
        ArrayList<PdfData> pdfDataList = new ArrayList<PdfData>();
        
        for (TopSheetRawData topSheetRawData:topSheetRawDataList) {
            System.out.println("DATA CONVERSION STARTED");
            ConvertTopSheetRawDataToPdfData convertTopSheetRawDataToPdfData = new ConvertTopSheetRawDataToPdfData(topSheetRawData);
            System.out.println("DATA CONVERSION COMPLETE");
            PdfData pdfData = convertTopSheetRawDataToPdfData.getPdfData();
            pdfDataList.add(pdfData);
            
        }
        return pdfDataList;
    }
    
}
