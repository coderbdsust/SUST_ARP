/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sust.admission.resultdataprocess;

import com.itextpdf.text.DocumentException;
import com.sust.admission.resultdataprocess.ConvertRawDataToPdfData;
import com.sust.admission.resultdataprocess.PdfData;
import com.sust.admission.resultdataprocess.ResultRawData;
import com.sust.admission.resultdataprocess.PersonResult;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Biswajit Debnath
 */
public class ProcessResultRawDataList {

    ArrayList<ResultRawData> meritResultList;

    public ProcessResultRawDataList(ArrayList<ResultRawData> meritResultList) {
        this.meritResultList = meritResultList;
    }

    public ArrayList<PdfData> getResultRawDataToPdfDataList() throws DocumentException {
        
        ArrayList<PdfData> pdfDataList = new ArrayList<PdfData>();
        for (ResultRawData resultRawData : meritResultList) {
            
            ConvertRawDataToPdfData convertRawDataToPdfData = new ConvertRawDataToPdfData(resultRawData);
            PdfData pdfData = convertRawDataToPdfData.getPdfData();
            pdfDataList.add(pdfData);
            
        }
        return pdfDataList;
    }
}
