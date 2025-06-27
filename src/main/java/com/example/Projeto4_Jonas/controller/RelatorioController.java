package com.example.Projeto4_Jonas.controller;

import com.example.Projeto4_Jonas.dto.RelatorioDTO;
import com.example.Projeto4_Jonas.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/relatorios")
@CrossOrigin(origins = "*")
public class RelatorioController {
    
    @Autowired
    private RelatorioService relatorioService;
    
    @GetMapping("/personalizado")
    public ResponseEntity<RelatorioDTO> gerarRelatorioPersonalizado() {
        RelatorioDTO relatorio = relatorioService.gerarRelatorioPersonalizado();
        return ResponseEntity.ok(relatorio);
    }
    
    @GetMapping("/periodo")
    public ResponseEntity<RelatorioDTO> gerarRelatorioPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim) {
        RelatorioDTO relatorio = relatorioService.gerarRelatorioPorPeriodo(dataInicio, dataFim);
        return ResponseEntity.ok(relatorio);
    }
}