package com.example.Projeto4_Jonas.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
    "openai.api.key=sk-test-fake-key-for-testing",
    "app.email.mock.enabled=true"
})
class AtividadeControllerIntegrationTest {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    void testListarAtividades() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/atividades", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    
    @Test
    void testEndpointExiste() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/atividades", String.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    @Test
    void testApplicationContext() {
        assertNotNull(restTemplate);
    }
}