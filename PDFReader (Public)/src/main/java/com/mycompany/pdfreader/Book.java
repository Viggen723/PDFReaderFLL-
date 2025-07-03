package com.mycompany.pdfreader;

/**
 *
 * @author Adam James Roy
 */

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.IOException;

public class Book
{
    private int code;
    private String title;
    private String genre;
    private String filePath = ""; // Enter personal filepath for desired pdf location
    
    public Book(int code, String title, String genre)
    {
        setBook(code, title, genre);
    }
    
    public Book(String title)
    {
        this.title = title;
    }
    
    public void setBook(int code, String title, String genre)
    {
        this.code = code;
        this.title = title;
        this.genre = genre;
    }
    
    public String readBook() throws IOException
    {
        String info = toString();
        System.out.println(info);
        System.out.println();
        
        File file = new File(filePath + title);
        PDDocument document = Loader.loadPDF(file);
        
        PDFTextStripper strippedText = new PDFTextStripper();
        String book = strippedText.getText(document);
        
        // Printing to shell for testing purposes
        // System.out.println(book); 
        
        document.close();
        
        return book;
        
    }
    
    public String getName()
    {
        return title;
    }
    
    public int getCode()
    {
        return code;
    }
    
    public String getGenre()
    {
        return genre;
    }
    
    public String toString()
    {
        return "Book: " + title + " Genre: " + genre + " Code:" + 
                code;
    }
}
