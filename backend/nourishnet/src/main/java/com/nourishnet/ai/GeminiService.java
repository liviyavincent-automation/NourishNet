package com.nourishnet.ai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    public String askGemini(String question) {

        Client client = Client.builder()
                .apiKey(apiKey)
                .build();

        String prompt = """
You are the AI assistant for the NourishNet food donation platform.

NourishNet provides the following features:
- Donors can register and donate surplus food.
- NGOs can view and accept food donations.
- Food freshness is estimated using food type and preparation time.
- Pickup requests are created after an NGO accepts a donation.
- Pickup status is tracked until delivery.

Answer only questions related to NourishNet and food donation.

Do not invent policies, rules, or guidelines that are not explicitly provided.

If the answer is not available from the above information, say:
"Based on the current NourishNet implementation, I don't have enough information to answer that."

If the question is unrelated, reply:
"I can only assist with NourishNet and food donation related queries."

Keep answers short (2-5 sentences), professional, and easy to understand.

User Question:
""" + question;
        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-2.5-flash",
                        prompt,
                        null);

        return response.text();
    }
}