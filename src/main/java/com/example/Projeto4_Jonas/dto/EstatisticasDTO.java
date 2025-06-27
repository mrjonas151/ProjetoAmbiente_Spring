package com.example.Projeto4_Jonas.dto;

import java.util.Map;

public class EstatisticasDTO {
    
    private Long totalAtividades;
    private Double impactoTotalPositivo;
    private Map<String, Long> atividadesPorTipo;
    private Double mediaImpactoDiario;
    
    public EstatisticasDTO() {}
    
    public EstatisticasDTO(Long totalAtividades, Double impactoTotalPositivo, 
                          Map<String, Long> atividadesPorTipo, Double mediaImpactoDiario) {
        this.totalAtividades = totalAtividades;
        this.impactoTotalPositivo = impactoTotalPositivo;
        this.atividadesPorTipo = atividadesPorTipo;
        this.mediaImpactoDiario = mediaImpactoDiario;
    }
    
    public Long getTotalAtividades() { return totalAtividades; }
    public void setTotalAtividades(Long totalAtividades) { this.totalAtividades = totalAtividades; }
    
    public Double getImpactoTotalPositivo() { return impactoTotalPositivo; }
    public void setImpactoTotalPositivo(Double impactoTotalPositivo) { this.impactoTotalPositivo = impactoTotalPositivo; }
    
    public Map<String, Long> getAtividadesPorTipo() { return atividadesPorTipo; }
    public void setAtividadesPorTipo(Map<String, Long> atividadesPorTipo) { this.atividadesPorTipo = atividadesPorTipo; }
    
    public Double getMediaImpactoDiario() { return mediaImpactoDiario; }
    public void setMediaImpactoDiario(Double mediaImpactoDiario) { this.mediaImpactoDiario = mediaImpactoDiario; }
}