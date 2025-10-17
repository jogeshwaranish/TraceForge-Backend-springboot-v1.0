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
        String modified = "In the context of making documentation files, make or modify the existing markdown based on the commit message in the following: " + commitmsg + "and the previous markdown whihc could be empty" + originalMarkdown + "lastly, only return markdown, no other words";
        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-2.5-flash",
                        modified,
                        null);
        originalMarkdown = response.text();
        return response.text();
    }
}