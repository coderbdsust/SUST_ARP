/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sust.admission.debug;

/**
 *
 * @author Biswajit Debnath
 */
public class Debugger {

    private boolean logPrintable;

    public Debugger() {
        setLogPrintable(true);
    }

    public Debugger(boolean logPrintable) {
        setLogPrintable(logPrintable);
    }

    public void debugLog(Object log) {
        if (isLogPrintable()) {
            System.out.println(log);
        }
    }

    public void debugLog(Object... values) {

        if (isLogPrintable()) {
            for (Object value : values) {
                System.out.print(value + " ");
            }
            System.out.println("");
        }
    }

    public boolean isLogPrintable() {
        return logPrintable;
    }

    public void setLogPrintable(boolean logPrintable) {
        this.logPrintable = logPrintable;
    }

}
