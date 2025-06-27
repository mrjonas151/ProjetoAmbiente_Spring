package com.example.Projeto4_Jonas.controller;

import com.example.Projeto4_Jonas.dto.EmailRequestDTO;
import com.example.Projeto4_Jonas.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "*")
public class EmailController {
    
    @Autowired
    private EmailService emailService;
    
    @PostMapping("/enviar")
    public ResponseEntity<Map<String, Object>> enviarRelatorio(
            @RequestParam String email,
            @Valid @RequestBody EmailRequestDTO emailRequest) {
        
        try {
            emailService.enviarRelatorioSustentavel(email, emailRequest.getMessage());
            
            Map<String, Object> response = new HashMap<>();
            response.put("sucesso", true);
            response.put("mensagem", "Relatório enviado com sucesso para " + email);
            response.put("destinatario", email);
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("sucesso", false);
            response.put("mensagem", "Erro ao enviar email: " + e.getMessage());
            response.put("destinatario", email);
            
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/verificar")
    public ResponseEntity<Map<String, Object>> verificarConfiguracao() {
        try {
            emailService.verificarConfiguracao();
            
            Map<String, Object> response = new HashMap<>();
            response.put("sucesso", true);
            response.put("mensagem", "Configuração do Mailtrap verificada - veja os logs");
            response.put("servidor", "live.smtp.mailtrap.io");
            response.put("porta", 587);
            response.put("remetente", "Topaz Contatos");
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("sucesso", false);
            response.put("mensagem", "Erro na configuração: " + e.getMessage());
            
            return ResponseEntity.badRequest().body(response);
        }
    }
}