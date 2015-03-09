/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sust.admission.pdfgeneration;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import java.util.HashMap;

/**
 *
 * @author Biswajit Debnath
 */
public class LetterFont {
    private HashMap<String,Font> fonts;
    private final int fontSizeStartLimit=5;
    private final int fontSizeEndLimit=25;
    
    public LetterFont(){
        addFont();
    }
    
    private void addFont(){
        fonts = new HashMap<String, Font>();
        addTimesRomanFont();
        addCourierFont();
        addHelveticaFont();
       // debugLog();
    }
    
    private void addTimesRomanFont(){
        String fontName = "TIMES_ROMAN";
        
        for(int fontSize=fontSizeStartLimit;fontSize<=fontSizeEndLimit;fontSize++){
            String fontTitle = fontName +"_"+fontSize+"_NORMAL";
           
            fonts.put(fontTitle, new Font(Font.FontFamily.TIMES_ROMAN,fontSize,Font.NORMAL,new BaseColor(0,0,0)));
        }
        
        for(int fontSize=fontSizeStartLimit;fontSize<=fontSizeEndLimit;fontSize++){
            String fontTitle = fontName +"_"+fontSize+"_BOLD";
           
            fonts.put(fontTitle, new Font(Font.FontFamily.TIMES_ROMAN,fontSize,Font.BOLD,new BaseColor(0,0,0)));
        }
        
        for(int fontSize=fontSizeStartLimit;fontSize<=fontSizeEndLimit;fontSize++){
            String fontTitle = fontName +"_"+fontSize+"_ITALIC";
            fonts.put(fontTitle, new Font(Font.FontFamily.TIMES_ROMAN,fontSize,Font.ITALIC,new BaseColor(0,0,0)));
        }
        
    }
    
     private void addCourierFont(){
        String fontName = "COURIER";
        
        for(int fontSize=fontSizeStartLimit;fontSize<=fontSizeEndLimit;fontSize++){
            String fontTitle = fontName +"_"+fontSize+"_NORMAL";
           
            fonts.put(fontTitle, new Font(Font.FontFamily.COURIER,fontSize,Font.NORMAL,new BaseColor(0,0,0)));
        }
        
        for(int fontSize=fontSizeStartLimit;fontSize<=fontSizeEndLimit;fontSize++){
            String fontTitle = fontName +"_"+fontSize+"_BOLD";
            fonts.put(fontTitle, new Font(Font.FontFamily.COURIER,fontSize,Font.BOLD,new BaseColor(0,0,0)));
        }
        
        for(int fontSize=fontSizeStartLimit;fontSize<=fontSizeEndLimit;fontSize++){
            String fontTitle = fontName +"_"+fontSize+"_ITALIC";
            fonts.put(fontTitle, new Font(Font.FontFamily.COURIER,fontSize,Font.ITALIC,new BaseColor(0,0,0)));
        }
    }
     
     private void addHelveticaFont(){
         
        String fontName = "HELVETICA";
        
        for(int fontSize=fontSizeStartLimit;fontSize<=fontSizeEndLimit;fontSize++){
            String fontTitle = fontName +"_"+fontSize+"_NORMAL";
           
            fonts.put(fontTitle, new Font(Font.FontFamily.HELVETICA,fontSize,Font.NORMAL,new BaseColor(0,0,0)));
        }
        
        for(int fontSize=fontSizeStartLimit;fontSize<=fontSizeEndLimit;fontSize++){
            String fontTitle = fontName +"_"+fontSize+"_BOLD";
            fonts.put(fontTitle, new Font(Font.FontFamily.HELVETICA,fontSize,Font.BOLD,new BaseColor(0,0,0)));
        }
        
        for(int fontSize=fontSizeStartLimit;fontSize<=fontSizeEndLimit;fontSize++){
            String fontTitle = fontName +"_"+fontSize+"_ITALIC";
            fonts.put(fontTitle, new Font(Font.FontFamily.HELVETICA,fontSize,Font.ITALIC,new BaseColor(0,0,0)));
        }
    }
    
    public Font getFont(String fontTitle, int fontSize, String type){
        return fonts.get(getFontName(fontTitle, fontSize, type));
    }
    
    public String getFontName(String fontTitle, int fontSize, String type){
        return fontTitle+"_"+fontSize+"_"+type;
    }
    public void debugLog(){
        System.out.println(fonts);
    }
}
