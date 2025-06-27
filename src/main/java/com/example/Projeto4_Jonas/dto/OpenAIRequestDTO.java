package com.example.Projeto4_Jonas.dto;

import java.util.List;

public class OpenAIRequestDTO {
    
    private String model;
    private List<Message> messages;
    private int max_tokens;
    private double temperature;
    
    public OpenAIRequestDTO() {}
    
    public OpenAIRequestDTO(String model, List<Message> messages, int max_tokens, double temperature) {
        this.model = model;
        this.messages = messages;
        this.max_tokens = max_tokens;
        this.temperature = temperature;
    }
    
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    
    public List<Message> getMessages() { return messages; }
    public void setMessages(List<Message> messages) { this.messages = messages; }
    
    public int getMax_tokens() { return max_tokens; }
    public void setMax_tokens(int max_tokens) { this.max_tokens = max_tokens; }
    
    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }
    
    public static class Message {
        private String role;
        private String content;
        
        public Message() {}
        
        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }
        
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
        
        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
    }
}