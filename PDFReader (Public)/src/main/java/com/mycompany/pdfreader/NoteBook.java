package com.mycompany.pdfreader;

/**
 *
 * @author Adam James Roy
 */

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.FileWriter;

public class NoteBook
{    
    // Please input your personal OpenAI API key in the following variable
    private String apiKey = "";
   
    public void writeToNote(String gptResult) throws Exception
    {
            String fileName = "NoteBook.txt";
            
            FileWriter output = new FileWriter(fileName, true);
            
            output.write(gptResult + "\n");
            output.write("\n");
            output.close();
            
            System.out.println("Successfully printed to file");
    }
            
    public String gptRequest(String newRequest) throws Exception
    {
        
        String desiredContent = newRequest + "というのは意味と読み方を５０文字以内説明して";
        
        // Creating a text in a JSON format to send to OpenAI
        String body = "{\n" +
            "    \"model\": \"gpt-4o\",\n" +
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
        
        // Extracting the text from the GPT response
        int beginning = response.body().indexOf("content") + 11; 
        int end = response.body().indexOf("\"", beginning);
        
        String contentAdd = newRequest + ": " + response.body().substring(beginning, end);
        
        return contentAdd; 
          
    }
}
