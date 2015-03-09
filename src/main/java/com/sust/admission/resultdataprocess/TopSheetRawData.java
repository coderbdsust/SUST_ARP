/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sust.admission.resultdataprocess;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class TopSheetRawData  implements Comparable<TopSheetRawData> {

    private ArrayList<PersonToInterview> topSheet;
    private int pdfNo;
    private int priority;
    private String posType;
    private String quotaName;
    private String meritListName;

    public TopSheetRawData(
            ArrayList<PersonToInterview> topSheet,
            int pdfNo,
            int priority,
            String posType,
            String quotaName,
            String meritListName) {
            setGroupData(topSheet, pdfNo, priority, posType, quotaName, meritListName);
    }

    public void setGroupData(
            ArrayList<PersonToInterview> topSheet,
            int pdfNo,
            int priority,
            String posType,
            String quotaName,
            String meritListName) {
        setTopSheet(topSheet);
        setPdfNo(pdfNo);
        setPriority(priority);
        setPosType(posType);
        setQuotaName(quotaName);
        setMeritListName(meritListName);
    }

    public void setTopSheet(ArrayList<PersonToInterview> topSheet) {
        this.topSheet = topSheet;
    }

    public ArrayList<PersonToInterview> getTopSheet() {
        return this.topSheet;
    }

    public void setPdfNo(int pdfNo) {
        this.pdfNo = pdfNo;
    }

    public int getPdfNo() {
        return this.pdfNo;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPosType(String posType) {
        this.posType = posType;
    }

    public String getPosType() {
        return this.posType;
    }

    public void setQuotaName(String quotaName) {
        this.quotaName = quotaName;
    }

    public String getQuotaName() {
        return this.quotaName;
    }

    public void setMeritListName(String meritListName) {
        this.meritListName = meritListName;
    }

    public String getMeritListName() {
        return this.meritListName;
    }

    @Override
    public int compareTo(TopSheetRawData r) {
        int comparePdfNo = ((TopSheetRawData) r).getPdfNo();
        int comparePriority = ((TopSheetRawData) r).getPriority();

        if (this.pdfNo != comparePdfNo) {
            return this.pdfNo - comparePdfNo;
        } else {
            return this.priority - comparePriority;
        }

    }

    @Override
    public String toString() {
        return String.format("PdfNo: %d MLN: %s PTYPE: %s QNAME: %s PR: %d",
                getPdfNo(),
                getMeritListName(),
                getPosType(),
                getQuotaName(),
                getPriority());
    }

}
