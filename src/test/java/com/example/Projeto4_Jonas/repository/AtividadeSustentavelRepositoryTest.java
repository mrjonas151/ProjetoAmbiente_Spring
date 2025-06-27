package com.example.Projeto4_Jonas.repository;

import com.example.Projeto4_Jonas.model.AtividadeSustentavel; 
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:h2:mem:testdb",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
class AtividadeSustentavelRepositoryTest {
    
    @Autowired
    private AtividadeSustentavelRepository repository;
    
    @Test
    void testRepositoryNaoNulo() {
        assertNotNull(repository);
    }
    
    @Test
    void testFindAll() {
        List<AtividadeSustentavel> atividades = repository.findAll();
        assertNotNull(atividades);
    }
    
    @Test
    void testSave() {
        AtividadeSustentavel atividade = new AtividadeSustentavel();
        atividade.setTipo("Teste");
        atividade.setDescricao("Teste desc");
        atividade.setData(LocalDateTime.now());
        atividade.setImpactoAmbiental(10.0);
        
        AtividadeSustentavel saved = repository.save(atividade);
        
        assertNotNull(saved);
        assertNotNull(saved.getId());
    }
}