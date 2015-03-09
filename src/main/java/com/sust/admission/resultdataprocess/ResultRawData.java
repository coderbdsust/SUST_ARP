/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sust.admission.resultdataprocess;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Biswajit Debnath
 */
public class ResultRawData implements Comparable<ResultRawData> {

    private ArrayList<PersonResult> meritResultList;
    private int pdfNo;
    private int priority;
    private String posType;
    private String quotaName;
    private String meritListName;

    public ResultRawData(
            ArrayList<PersonResult> meritResultList,
            int pdfNo,
            int priority,
            String posType,
            String quotaName,
            String meritListName) {
        setGroupData(meritResultList, pdfNo, priority, posType, quotaName, meritListName);
    }

    public void setGroupData(
            ArrayList<PersonResult> meritResultList,
            int pdfNo,
            int priority,
            String posType,
            String quotaName,
            String meritListName) {
        setResultList(meritResultList);
        setPdfNo(pdfNo);
        setPriority(priority);
        setPosType(posType);
        setQuotaName(quotaName);
        setMeritListName(meritListName);
    }

    public void setResultList(ArrayList<PersonResult> meritResultList) {
        this.meritResultList = meritResultList;
    }

    public ArrayList<PersonResult> getResultList() {
        return this.meritResultList;
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
    public int compareTo(ResultRawData r) {
        int comparePdfNo = ((ResultRawData) r).getPdfNo();
        int comparePriority = ((ResultRawData) r).getPriority();

        if (this.pdfNo != comparePdfNo) {
            return this.pdfNo - comparePdfNo;
        } else {
            return this.priority - comparePriority;
        }

    }

    public String toString() {
        return String.format("PdfNo: %d MLN: %s PTYPE: %s QNAME: %s PR: %d",
                getPdfNo(),
                getMeritListName(),
                getPosType(),
                getQuotaName(),
                getPriority());
    }

}
