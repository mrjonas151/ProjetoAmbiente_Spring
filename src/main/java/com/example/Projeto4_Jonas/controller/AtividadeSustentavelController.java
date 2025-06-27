package com.example.Projeto4_Jonas.controller;

import com.example.Projeto4_Jonas.dto.AtividadeSustentavelDTO;
import com.example.Projeto4_Jonas.service.AtividadeSustentavelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/atividades")
@CrossOrigin(origins = "*")
public class AtividadeSustentavelController {
    
    @Autowired
    private AtividadeSustentavelService service;
    
    @GetMapping
    public ResponseEntity<List<AtividadeSustentavelDTO>> listarTodas() {
        List<AtividadeSustentavelDTO> atividades = service.listarTodas();
        return ResponseEntity.ok(atividades);
    }
    
    @GetMapping("/paginacao")
    public ResponseEntity<Page<AtividadeSustentavelDTO>> listarComPaginacao(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "data") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        
        Sort.Direction direction = sortDir.equalsIgnoreCase("desc") ? 
            Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<AtividadeSustentavelDTO> atividades = service.listarComPaginacao(pageable);
        
        return ResponseEntity.ok(atividades);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AtividadeSustentavelDTO> buscarPorId(@PathVariable Long id) {
        AtividadeSustentavelDTO atividade = service.buscarPorId(id);
        return ResponseEntity.ok(atividade);
    }
    
    @PostMapping
    public ResponseEntity<AtividadeSustentavelDTO> criar(@Valid @RequestBody AtividadeSustentavelDTO dto) {
        AtividadeSustentavelDTO atividadeCriada = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(atividadeCriada);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AtividadeSustentavelDTO> atualizar(
            @PathVariable Long id, 
            @Valid @RequestBody AtividadeSustentavelDTO dto) {
        AtividadeSustentavelDTO atividadeAtualizada = service.atualizar(id, dto);
        return ResponseEntity.ok(atividadeAtualizada);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/buscar/tipo")
    public ResponseEntity<List<AtividadeSustentavelDTO>> buscarPorTipo(@RequestParam String tipo) {
        List<AtividadeSustentavelDTO> atividades = service.buscarPorTipo(tipo);
        return ResponseEntity.ok(atividades);
    }
    
    @GetMapping("/buscar/periodo")
    public ResponseEntity<List<AtividadeSustentavelDTO>> buscarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim) {
        List<AtividadeSustentavelDTO> atividades = service.buscarPorPeriodo(dataInicio, dataFim);
        return ResponseEntity.ok(atividades);
    }
    
    @GetMapping("/buscar/impacto")
    public ResponseEntity<List<AtividadeSustentavelDTO>> buscarPorImpactoMinimo(
            @RequestParam Double impactoMinimo) {
        List<AtividadeSustentavelDTO> atividades = service.buscarPorImpactoMinimo(impactoMinimo);
        return ResponseEntity.ok(atividades);
    }
}