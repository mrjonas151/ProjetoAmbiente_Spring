package com.example.Projeto4_Jonas.controller;

import com.example.Projeto4_Jonas.dto.EstatisticasDTO;
import com.example.Projeto4_Jonas.service.EstatisticasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/estatisticas")
@CrossOrigin(origins = "*")
public class EstatisticasController {
    
    @Autowired
    private EstatisticasService service;
    
    @GetMapping
    public ResponseEntity<EstatisticasDTO> obterEstatisticasGerais() {
        EstatisticasDTO estatisticas = service.calcularEstatisticasGerais();
        return ResponseEntity.ok(estatisticas);
    }
    
    @GetMapping("/periodo")
    public ResponseEntity<EstatisticasDTO> obterEstatisticasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim) {
        EstatisticasDTO estatisticas = service.calcularEstatisticas(dataInicio, dataFim);
        return ResponseEntity.ok(estatisticas);
    }
    
    @GetMapping("/impacto-mensal")
    public ResponseEntity<Double> obterImpactoMensal() {
        Double impactoMensal = service.calcularImpactoTotalDoMes();
        return ResponseEntity.ok(impactoMensal);
    }
}