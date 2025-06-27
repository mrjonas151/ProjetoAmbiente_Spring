package com.example.Projeto4_Jonas.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmailRequestDTOTest {
    
    @Test
    void testConstrutorVazio() {
        EmailRequestDTO dto = new EmailRequestDTO();
        
        assertNotNull(dto);
        assertNull(dto.getMessage());
    }
    
    
    @Test
    void testConstrutorComParametro() {
        EmailRequestDTO dto = new EmailRequestDTO("Teste mensagem");
        
        assertEquals("Teste mensagem", dto.getMessage());
    }
    
    @Test
    void testSetMessage() {
        EmailRequestDTO dto = new EmailRequestDTO();
        
        dto.setMessage("Nova mensagem");
        
        assertEquals("Nova mensagem", dto.getMessage());
    }
    
    @Test
    void testMessageNull() {
        EmailRequestDTO dto = new EmailRequestDTO();
        
        dto.setMessage(null);
        
        assertNull(dto.getMessage());
    }
    
    @Test
    void testMessageEmpty() {
        EmailRequestDTO dto = new EmailRequestDTO();
        
        dto.setMessage("");
        
        assertEquals("", dto.getMessage());
    }
}