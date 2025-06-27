package com.example.Projeto4_Jonas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

public class AtividadeSustentavelDTO {
    
    private Long id;
    
    @NotBlank(message = "Tipo da atividade é obrigatório")
    private String tipo;
    
    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;
    
    @NotNull(message = "Data é obrigatória")
    private LocalDateTime data;
    
    @PositiveOrZero(message = "Impacto ambiental deve ser positivo ou zero")
    private Double impactoAmbiental;
    
    private String unidadeImpacto;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    
  public AtividadeSustentavelDTO() {}
    
    public AtividadeSustentavelDTO(String tipo, String descricao, LocalDateTime data, 
                                  Double impactoAmbiental, String unidadeImpacto) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.data = data;
        this.impactoAmbiental = impactoAmbiental;
        this.unidadeImpacto = unidadeImpacto;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }
    
    public Double getImpactoAmbiental() { return impactoAmbiental; }
    public void setImpactoAmbiental(Double impactoAmbiental) { this.impactoAmbiental = impactoAmbiental; }
    
    public String getUnidadeImpacto() { return unidadeImpacto; }
    public void setUnidadeImpacto(String unidadeImpacto) { this.unidadeImpacto = unidadeImpacto; }
    
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
    
    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }
}