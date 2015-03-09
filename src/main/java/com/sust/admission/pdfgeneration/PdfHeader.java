/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sust.admission.pdfgeneration;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.sust.admission.constant.PdfConstant;
import java.io.IOException;

/**
 *
 * @author Biswajit Debnath
 */
public class PdfHeader {

    Image image = null;
    LetterFont letterFont;
    String TIMES_ROMAN = "TIMES_ROMAN";
    String COURIER = "COURIER";
    String HELVETICA = "HELVETICA";
    String BOLD = "BOLD";
    String ITALIC = "ITALIC";
    String NORMAL = "NORMAL";

    public PdfHeader(){
        initializeFont();
        initializeSustLogo();
    }

    private void initializeFont() {
        letterFont = new LetterFont();
    }

    private void initializeSustLogo(){
        try {
            
            String path = PdfConstant.IMAGE_URL;
            image = Image.getInstance(path);
            
        } catch (BadElementException error) {
            System.out.println("Bad Element Exception Found When Loading Image!");
        } catch (IOException error) {
            System.out.println("IO Exception Found When Loading Image!");
        }catch(Exception error){
            System.out.println("Exception Found In Image Loading!");
        }
    }

    public Image getSustLogo() {
        image.scaleAbsolute(75f, 75f);

        return image;
    }

    public Paragraph getUniversityName() {

        Paragraph pdfTitleParagraph = new Paragraph(
                PdfConstant.UNIVERSITY_NAME,
                letterFont.getFont(TIMES_ROMAN, 23, BOLD)
        );

        float titleSpacingFromTop = -85.0f;
        pdfTitleParagraph.setSpacingBefore(titleSpacingFromTop);
        pdfTitleParagraph.setIndentationLeft(5);
        pdfTitleParagraph.setAlignment(Element.ALIGN_RIGHT);

        return pdfTitleParagraph;
    }

    public Paragraph getAdmissionName() {
        Paragraph pdfSubTitleParagraph = new Paragraph(
                PdfConstant.ADMISSION_TITLE,
                letterFont.getFont(TIMES_ROMAN, 22, NORMAL)
        );

        float subTitleSpacingFromTop = -2f;
        pdfSubTitleParagraph.setSpacingBefore(subTitleSpacingFromTop);
        pdfSubTitleParagraph.setAlignment(Element.ALIGN_CENTER);
        pdfSubTitleParagraph.setIndentationLeft(65);

        return pdfSubTitleParagraph;
    }

    public Paragraph getMeritTitle(String meritTitle) {
        Paragraph meritTitleParagraph = new Paragraph(meritTitle, letterFont.getFont(TIMES_ROMAN, 9, BOLD));
        meritTitleParagraph.setAlignment(Element.ALIGN_CENTER);
        meritTitleParagraph.setIndentationLeft(65);
        return meritTitleParagraph;
    }
}
