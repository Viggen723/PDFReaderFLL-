package com.mycompany.pdfreader;

/**
 * // Main method that is used for testing (text-based)
 * @author Adam James Roy
 */

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PDFReader 
{

    public static void main(String[] args) throws IOException, InterruptedException
    {
      
        Book book1 = new Book(1, "吾輩は猫である", "Novel");
        
        Library lib = new Library();
        lib.add(book1);
        
        LibraryMenu lm = new LibraryMenu(lib);   
        lm.LibraryMenuShell();
        
        String apiKey = System.getenv("OPENAI_API_KEY"); // Personal key hidden with environment variable
        
        String desiredContent = "踏襲　５０文字以内説明して"; // Test in shell

        String body = "{\n" +
            "    \"model\": \"gpt-3.5-turbo\",\n" +
            "    \"messages\": [\n" +
            "        {\n" +
            "            \"role\": \"user\",\n" +
            "            \"content\": \"" + desiredContent + "\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";
    
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/chat/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        
        int beginning = response.body().indexOf("content") + 11;

        int end = response.body().indexOf("\"", beginning);

        String contentAdd = response.body().substring(beginning, end);
        
        System.out.println(contentAdd);
        
        NoteBook test = new NoteBook();
        try
        {
        test.writeToNote(contentAdd + "\n"); 
        }
        
        catch (Exception e)
        {
            
        }
    }
}
