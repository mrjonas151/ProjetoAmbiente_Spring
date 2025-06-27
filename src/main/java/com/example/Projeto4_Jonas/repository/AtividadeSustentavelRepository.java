package com.example.Projeto4_Jonas.repository;

import com.example.Projeto4_Jonas.model.AtividadeSustentavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AtividadeSustentavelRepository extends JpaRepository<AtividadeSustentavel, Long> {
    
    List<AtividadeSustentavel> findByTipoContainingIgnoreCase(String tipo);
    
    List<AtividadeSustentavel> findByDataBetween(LocalDateTime dataInicio, LocalDateTime dataFim);
    
    @Query("SELECT a FROM AtividadeSustentavel a WHERE a.impactoAmbiental >= :impactoMinimo")
    List<AtividadeSustentavel> findByImpactoAmbientalMinimo(@Param("impactoMinimo") Double impactoMinimo);
    
    @Query("SELECT COALESCE(SUM(a.impactoAmbiental), 0.0) FROM AtividadeSustentavel a WHERE a.data BETWEEN :dataInicio AND :dataFim")
    Double calcularImpactoTotalPeriodo(@Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim);
    
    @Query("SELECT COUNT(a) FROM AtividadeSustentavel a WHERE LOWER(a.tipo) LIKE LOWER(CONCAT('%', :tipo, '%'))")
    Long contarAtividadesPorTipo(@Param("tipo") String tipo);
}