package com.TraceForge.AI.Service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class GeminiService {

    public String generateMarkdown(String commitmsg) {
        Dotenv dotenv = Dotenv.load();
        String apikey = dotenv.get("GOOGLE_API_KEY");
        Client client = Client.builder().apiKey(apikey).build();
        String originalMarkdown = "";
        String context = "In the context of making documentation, make or modify the exisitng markdown based on the git hub repository information given to you in the following: ";
        String modified = context + commitmsg + "and the previous markdown which could be empty" + originalMarkdown + "lastly, only return markdown, no other words";
        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-2.5-flash",
                        modified,
                        null);
        originalMarkdown = response.text();
        return response.text();
    }
}