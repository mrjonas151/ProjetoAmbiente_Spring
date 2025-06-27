package com.example.Projeto4_Jonas.service;

import com.example.Projeto4_Jonas.dto.AtividadeSustentavelDTO;
import com.example.Projeto4_Jonas.exception.ResourceNotFoundException;
import com.example.Projeto4_Jonas.mapper.AtividadeSustentavelMapper;
import com.example.Projeto4_Jonas.model.AtividadeSustentavel;
import com.example.Projeto4_Jonas.repository.AtividadeSustentavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AtividadeSustentavelService {
    
    @Autowired
    private AtividadeSustentavelRepository repository;
    
    @Autowired
    private AtividadeSustentavelMapper mapper;
    
    public List<AtividadeSustentavelDTO> listarTodas() {
        List<AtividadeSustentavel> atividades = repository.findAll();
        return atividades.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
    
    public Page<AtividadeSustentavelDTO> listarComPaginacao(Pageable pageable) {
        Page<AtividadeSustentavel> atividades = repository.findAll(pageable);
        return atividades.map(mapper::toDTO);
    }
    
    public AtividadeSustentavelDTO buscarPorId(Long id) {
        AtividadeSustentavel atividade = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atividade não encontrada com ID: " + id));
        return mapper.toDTO(atividade);
    }
    
    public AtividadeSustentavelDTO criar(AtividadeSustentavelDTO dto) {
        AtividadeSustentavel atividade = mapper.toEntity(dto);
        AtividadeSustentavel atividadeSalva = repository.save(atividade);
        return mapper.toDTO(atividadeSalva);
    }
    
    public AtividadeSustentavelDTO atualizar(Long id, AtividadeSustentavelDTO dto) {
        AtividadeSustentavel atividadeExistente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atividade não encontrada com ID: " + id));
        
        atividadeExistente.setTipo(dto.getTipo());
        atividadeExistente.setDescricao(dto.getDescricao());
        atividadeExistente.setData(dto.getData());
        atividadeExistente.setImpactoAmbiental(dto.getImpactoAmbiental());
        atividadeExistente.setUnidadeImpacto(dto.getUnidadeImpacto());
        
        AtividadeSustentavel atividadeAtualizada = repository.save(atividadeExistente);
        return mapper.toDTO(atividadeAtualizada);
    }
    
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Atividade não encontrada com ID: " + id);
        }
        repository.deleteById(id);
    }
    
    public List<AtividadeSustentavelDTO> buscarPorTipo(String tipo) {
        List<AtividadeSustentavel> atividades = repository.findByTipoContainingIgnoreCase(tipo);
        return atividades.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
    
    public List<AtividadeSustentavelDTO> buscarPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) {
        List<AtividadeSustentavel> atividades = repository.findByDataBetween(dataInicio, dataFim);
        return atividades.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
    
    public List<AtividadeSustentavelDTO> buscarPorImpactoMinimo(Double impactoMinimo) {
        List<AtividadeSustentavel> atividades = repository.findByImpactoAmbientalMinimo(impactoMinimo);
        return atividades.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}