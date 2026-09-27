package org.example.capstone2.Service;

import com.google.api.client.util.Value;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.stereotype.Service;

@Service
public class AiService {
    private final Client client;

    public AiService() {
        client = Client.builder()
                .apiKey("AIzaSyA0F9AdJ3AP41CDS_DMzqCTw9ilOMOyiXc")
                .build();
    }

    public String findSpeciality(String description){
        String prompt = """
                You are a legal specialty classifier for a Saudi legal services platform.

                Read the user's legal problem and return ONLY the most appropriate legal specialty.
               

                Possible specialties:
                Labor Law
                Family Law
                Criminal Law
                Commercial Law
                Real Estate Law
                Civil Law
                Administrative Law
                Intellectual Property Law
                Personal Status Law
                
                Response language:
                    - If the user's problem is written in Arabic, return the specialty in Arabic.
                    - If the user's problem is written in English, return the specialty in English.
                    - Return ONLY the specialty. Do not provide explanations.

                User problem:
                """ + description;

        GenerateContentResponse response = client.models.generateContent(
                "gemini-3.5-flash-lite",
                prompt,
                null
        );

        return response.text();
    }

}
