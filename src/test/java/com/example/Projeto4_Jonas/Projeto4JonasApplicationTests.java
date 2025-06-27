package com.example.Projeto4_Jonas;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "openai.api.key=sk-test-key",
    "spring.mail.host=localhost",
    "app.email.mock.enabled=true"
})
class Projeto4JonasApplicationTest {
    
    
    @Test
    void contextLoads() {
    }
    
    @Test
    void applicationStarts() {
        assert true;
    }
}