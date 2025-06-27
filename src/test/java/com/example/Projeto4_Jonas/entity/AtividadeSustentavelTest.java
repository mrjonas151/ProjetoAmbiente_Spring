package com.example.Projeto4_Jonas.entity;

import com.example.Projeto4_Jonas.model.AtividadeSustentavel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AtividadeSustentavelTest {
    
    @Test
    void testConstrutor() {
        AtividadeSustentavel entity = new AtividadeSustentavel();
        assertNotNull(entity);
    }
    
    @Test
    void testSettersBasicos() {
        AtividadeSustentavel entity = new AtividadeSustentavel();
        entity.setTipo("Plantio");
        entity.setDescricao("Plantar mudas");
        entity.setImpactoAmbiental(5.5);
        
        assertEquals("Plantio", entity.getTipo());
        assertEquals("Plantar mudas", entity.getDescricao());
        assertEquals(5.5, entity.getImpactoAmbiental());
    }
    
    @Test
    void testGettersBasicos() {
        AtividadeSustentavel entity = new AtividadeSustentavel();
        entity.setTipo("Economia de Agua");
        entity.setDescricao("Banho rapido");
        entity.setImpactoAmbiental(10.0);
        
        assertEquals("Economia de Agua", entity.getTipo());
        assertEquals("Banho rapido", entity.getDescricao());
        assertEquals(10.0, entity.getImpactoAmbiental());
    }
}