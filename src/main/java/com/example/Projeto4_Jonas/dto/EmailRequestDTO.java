package com.example.Projeto4_Jonas.dto;

import jakarta.validation.constraints.NotBlank;

public class EmailRequestDTO {
    
    @NotBlank(message = "Mensagem é obrigatória")
    private String message;
    
    public EmailRequestDTO() {}
    
    public EmailRequestDTO(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}