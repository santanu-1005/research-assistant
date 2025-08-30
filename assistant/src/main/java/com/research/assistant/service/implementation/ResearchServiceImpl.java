package com.research.assistant.service.implementation;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.research.assistant.dto.GeminiResponse;
import com.research.assistant.dto.ResearchRequest;
import com.research.assistant.service.ResearchService;

@Service
public class ResearchServiceImpl implements ResearchService{
    @Value("${gemini.api.url}")
    private String geminiUrl;

    @Value("${gemini.api.key}")
    private String geminiKey;
    
    private final WebClient webClient;
    private final ObjectMapper objectMapper;
    public ResearchServiceImpl(WebClient.Builder webClientBuilder, ObjectMapper objectMapper){
        this.webClient = webClientBuilder.build();
        this.objectMapper = objectMapper;
    }

    @Override
    public String processContent(ResearchRequest request){
        // Build The Prompt
        String prompt = buildPrompt(request);

        // Query the AI Model API
        Map<String, Object> requestBody = Map.of(
            "contents", new Object[] {
                Map.of(
                    "parts", new Object[]{
                        Map.of(
                            "text", prompt
                        )
                    }
                )
            }
        );
        
        String response = webClient.post()
                                .uri(geminiUrl+geminiKey)
                                .bodyValue(requestBody)
                                .retrieve()
                                .bodyToMono(String.class)
                                .block();

        // Parse and return the response
        return extractTextFromResponse(response);
    }

    private String buildPrompt(ResearchRequest request){
        StringBuilder prompt = new StringBuilder();
        switch (request.getOperation()) {
            case "summerize":
                prompt.append("Provide a clear and concise summary of the following text in a few sentenes:\n\n");
                break;
            
            case "suggest" :
                prompt.append("Based on the following content: suggest related topics and furthur reading. Format the response with clear headings and bulletpoints: \n\n");
                break;
        
            default:
                throw new IllegalArgumentException("Unknown Operation" + request.getOperation());
        }
        prompt.append(request.getContent());
        return prompt.toString();
    }

    private String extractTextFromResponse(String response){
        try {
            GeminiResponse geminiResponse = objectMapper.readValue(response, GeminiResponse.class);
            if(geminiResponse.getCandidates() != null && !geminiResponse.getCandidates().isEmpty()){
                GeminiResponse.Candidate firstCandidate = geminiResponse.getCandidates().get(0);
                if(firstCandidate.getContent() != null 
                    && firstCandidate.getContent().getParts() != null 
                    && !firstCandidate.getContent().getParts().isEmpty()){
                    return firstCandidate.getContent().getParts().get(0).getText();
                }
            }
            return "No Content Found In Response";
        } catch (Exception e) {
            return "Error Parsing: " + e.getMessage();
        }
    }
}
