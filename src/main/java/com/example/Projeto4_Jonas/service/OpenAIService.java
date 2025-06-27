package com.example.Projeto4_Jonas.service;

import com.example.Projeto4_Jonas.dto.OpenAIRequestDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
public class OpenAIService {
    
    private static final Logger logger = LoggerFactory.getLogger(OpenAIService.class);
    
    @Value("${openai.api.key}")
    private String openAiApiKey;
    
    @Value("${openai.api.url}")
    private String openAiApiUrl;
    
    private final WebClient webClient;
    private final ObjectMapper objectMapper;
    
    public OpenAIService(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClient = webClientBuilder.build();
        this.objectMapper = objectMapper;
    }
    
    public String gerarRelatorio(String promptUsuario) {
       
        
        if (openAiApiKey == null || openAiApiKey.trim().isEmpty() || 
            openAiApiKey.equals("YOUR_OPENAI_API_KEY_HERE")) {
            throw new RuntimeException("Chave da OpenAI não configurada. Configure 'openai.api.key' no application.properties");
        }
        
        if (!openAiApiKey.startsWith("sk-")) {
            throw new RuntimeException("Chave da OpenAI inválida. Deve começar com 'sk-'");
        }
        
        try {
            
            OpenAIRequestDTO request = new OpenAIRequestDTO(
                "gpt-3.5-turbo-0125",
                List.of(
                    new OpenAIRequestDTO.Message("system", 
                        "Você é um consultor ambiental brasileiro especialista em sustentabilidade, " +
                        "amigável e muito motivador. Analise as atividades sustentáveis do usuário e " +
                        "forneça um relatório personalizado e detalhado com parabenização específica " +
                        "e sugestões práticas para melhorar ainda mais. Seja positivo, específico e " +
                        "dê dicas simples de implementar no dia a dia brasileiro. Use linguagem casual, " +
                        "motivadora e inclua emojis. Máximo 400 palavras. Foque nos impactos reais " +
                        "e números apresentados."),
                    new OpenAIRequestDTO.Message("user", promptUsuario)
                ),
                800, 
                0.7
            );
            
            String response = webClient.post()
                .uri(openAiApiUrl)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + openAiApiKey)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .block();
            
            String relatorioGerado = extrairTextoResposta(response);
            
            return relatorioGerado;
            
        } catch (WebClientResponseException e) {
            logger.error("Erro HTTP da OpenAI: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
            
            if (e.getStatusCode().value() == 401) {
                throw new RuntimeException("Chave da OpenAI inválida ou expirada. Verifique sua chave em https://platform.openai.com/api-keys");
            } else if (e.getStatusCode().value() == 429) {
                throw new RuntimeException("Limite de uso da OpenAI atingido. Verifique seu saldo em https://platform.openai.com/usage");
            } else if (e.getStatusCode().value() == 403) {
                throw new RuntimeException("Acesso negado pela OpenAI. Verifique se sua conta tem créditos disponíveis.");
            } else {
                throw new RuntimeException("Erro na API da OpenAI: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            }
            
        } catch (Exception e) {
            logger.error("Erro inesperado ao chamar OpenAI: {}", e.getMessage(), e);
            throw new RuntimeException("Erro de conexão com a OpenAI: " + e.getMessage());
        }
    }
    
    private String extrairTextoResposta(String response) {
        try {
            JsonNode jsonNode = objectMapper.readTree(response);
            JsonNode choices = jsonNode.path("choices");
            
            if (choices.isEmpty()) {
                throw new RuntimeException("Resposta da OpenAI não contém escolhas válidas");
            }
            
            String conteudo = choices.get(0)
                          .path("message")
                          .path("content")
                          .asText();
                          
            if (conteudo == null || conteudo.trim().isEmpty()) {
                throw new RuntimeException("Conteúdo da resposta da OpenAI está vazio");
            }
            
                jsonNode.path("usage").path("total_tokens").asText("N/A"));
            
            return conteudo;
            
        } catch (Exception e) {
            logger.error("Erro ao processar resposta da OpenAI: {}", e.getMessage());
            throw new RuntimeException("Erro ao processar resposta da OpenAI: " + e.getMessage());
        }
    }
}