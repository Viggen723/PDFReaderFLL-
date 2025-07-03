package com.mycompany.pdfreader;

/**
 * A text based Library menu used in early stages of development
 * @author Adam James Roy
 */

import java.io. IOException;
import java.util.Scanner;

public class LibraryMenu
{
    private int choice = 0;
    private Library l;
    
    public LibraryMenu(Library l) throws IOException
    {
        this.l = l;
    }
    
    public void main(String[] args)
    {
        l.addFolder();
    }
    
    public void LibraryMenuShell() throws IOException
    {
        Scanner kb = new Scanner(System.in);
        
        System.out.println("Welcome to your own personal library!");
        System.out.println();
        
        System.out.println("What would you like to do today?");
        System.out.println();
        
        while (choice != -1)
        {    
          
            System.out.println("*Press 1 to check library contents*");
            System.out.println("*Press 2 to check number of books*");
            System.out.println("*Press 3 to take out a book*");
            System.out.println();
            System.out.println("-1 to exit");
            System.out.println();
           
            this.choice = kb.nextInt();
        
        
            switch (choice)
            {
                case 1:
                    System.out.println("Here are the contents: ");
                    System.out.println();
                    System.out.println();
                
                    l.checkInventory();
                    System.out.println();
                    break;
                
                case 2:
                    System.out.println("The total number of books is " + l.getCount());
                    System.out.println();
                    break;
            
                case 3:
                    System.out.print("Please enter the code of the desired book: ");
                    int bookCode = kb.nextInt() - 1;
                    System.out.println();
                    System.out.println("Here is the book!");
                    System.out.println();
                    Book desired = l.getBook(bookCode);
                    desired.readBook();
                    System.out.println();
                    break;
            }
           
        }
    }
}
