package com.example.Projeto4_Jonas.mapper;

import com.example.Projeto4_Jonas.dto.AtividadeSustentavelDTO;
import com.example.Projeto4_Jonas.model.AtividadeSustentavel;
import org.springframework.stereotype.Component;

@Component
public class AtividadeSustentavelMapper {
    
    public AtividadeSustentavelDTO toDTO(AtividadeSustentavel entity) {
        if (entity == null) {
            return null;
        }
        
        AtividadeSustentavelDTO dto = new AtividadeSustentavelDTO();
        dto.setId(entity.getId());
        dto.setTipo(entity.getTipo());
        dto.setDescricao(entity.getDescricao());
        dto.setData(entity.getData());
        dto.setImpactoAmbiental(entity.getImpactoAmbiental());
        dto.setUnidadeImpacto(entity.getUnidadeImpacto());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setDataAtualizacao(entity.getDataAtualizacao());
        
        return dto;
    }
    
    public AtividadeSustentavel toEntity(AtividadeSustentavelDTO dto) {
        if (dto == null) {
            return null;
        }
        
        AtividadeSustentavel entity = new AtividadeSustentavel();
        entity.setId(dto.getId());
        entity.setTipo(dto.getTipo());
        entity.setDescricao(dto.getDescricao());
        entity.setData(dto.getData());
        entity.setImpactoAmbiental(dto.getImpactoAmbiental());
        entity.setUnidadeImpacto(dto.getUnidadeImpacto());
        
        return entity;
    }
}