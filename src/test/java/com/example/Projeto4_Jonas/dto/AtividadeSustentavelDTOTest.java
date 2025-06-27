package com.example.Projeto4_Jonas.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AtividadeSustentavelDTOTest {
    
    @Test
    void testConstrutor() {
        AtividadeSustentavelDTO dto = new AtividadeSustentavelDTO();
        assertNotNull(dto);
    }
    
    
    @Test
    void testSettersAndGetters() {
        AtividadeSustentavelDTO dto = new AtividadeSustentavelDTO();
        dto.setId(1L);
        dto.setTipo("Economia de Água");
        dto.setDescricao("Banho rápido");
        dto.setImpactoAmbiental(50.0);
        
        assertEquals(1L, dto.getId());
        assertEquals("Economia de Água", dto.getTipo());
        assertEquals("Banho rápido", dto.getDescricao());
        assertEquals(50.0, dto.getImpactoAmbiental());
    }
    
    @Test
    void testImpactoNull() {
        AtividadeSustentavelDTO dto = new AtividadeSustentavelDTO();
        dto.setImpactoAmbiental(null);
        assertNull(dto.getImpactoAmbiental());
    }
}