package com.mycompany.pdfreader;

/**
 *
 * @author Adam James Roy
 */
import java.io.File;
import java.util.*;
public class Library 
{
    private static ArrayList<Book> library = new ArrayList(); 
    private static int bookCount;
    private static String filePath = ""; // Enter personal filepath for desired pdf location
    
    public Library()
    {
        
    }
    
    public void add(Book b)
    {
        library.add(b.getCode(), b);
        bookCount++;
    }
    
    // Creating a function in order to add every book in local folder on the GUI
    public void addFolder() 
    {
        int count = 1;
        
        File directory = new File(filePath); // Enter file path for saved pdfs
        File[] files = directory.listFiles();
        
        if (files != null)
        {
            for (File fileName : files)
            {
                Book newBook = new Book(count, fileName.getName(), "Novel");
                System.out.println(newBook.toString());
                count++;
                library.add(newBook.getCode() - 1, newBook);
                bookCount++;
            }
        }
    }
    
    public String checkInventory()
    {
        String inventory = "";
        
        for (int i = 0; i < library.size(); i++)
        {
            if (library.get(i) != null)
            {
                inventory += "Book " + (i + 1) + ": " + library.get(i).getName() +
                        " (" + library.get(i).getGenre() + ")\n";
            }
        }
        
        return inventory;
    }
    
    public Book getBook(int code)
    {
        return library.get(code);
    }
    
    public int getCount()
    {
        return bookCount;
    }
}
