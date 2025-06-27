package com.example.Projeto4_Jonas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Entity
@Table(name = "atividades_sustentaveis")
public class AtividadeSustentavel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Tipo da atividade é obrigatório")
    @Column(nullable = false)
    private String tipo;
    
    @NotBlank(message = "Descrição é obrigatória")
    @Column(nullable = false, length = 500)
    private String descricao;
    
    @NotNull(message = "Data é obrigatória")
    @Column(nullable = false)
    private LocalDateTime data;
    
    @PositiveOrZero(message = "Impacto ambiental deve ser positivo ou zero")
    @Column(name = "impacto_ambiental")
    private Double impactoAmbiental;
    
    @Column(name = "unidade_impacto")
    private String unidadeImpacto; 
    
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;
    
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
    
    public AtividadeSustentavel() {}
    
    public AtividadeSustentavel(String tipo, String descricao, LocalDateTime data, 
                               Double impactoAmbiental, String unidadeImpacto) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.data = data;
        this.impactoAmbiental = impactoAmbiental;
        this.unidadeImpacto = unidadeImpacto;
    }
    
    @PrePersist
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
        dataAtualizacao = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        dataAtualizacao = LocalDateTime.now();
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