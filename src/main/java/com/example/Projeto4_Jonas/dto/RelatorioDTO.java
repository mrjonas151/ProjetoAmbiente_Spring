package com.example.Projeto4_Jonas.dto;

public class RelatorioDTO {
    
    private String relatorio;
    private Long totalAtividades;
    private String periodoAnalise;
    
    public RelatorioDTO() {}
    
    public RelatorioDTO(String relatorio, Long totalAtividades, String periodoAnalise) {
        this.relatorio = relatorio;
        this.totalAtividades = totalAtividades;
        this.periodoAnalise = periodoAnalise;
    }
    
    public String getRelatorio() { return relatorio; }
    public void setRelatorio(String relatorio) { this.relatorio = relatorio; }
    
    public Long getTotalAtividades() { return totalAtividades; }
    public void setTotalAtividades(Long totalAtividades) { this.totalAtividades = totalAtividades; }
    
    public String getPeriodoAnalise() { return periodoAnalise; }
    public void setPeriodoAnalise(String periodoAnalise) { this.periodoAnalise = periodoAnalise; }
}