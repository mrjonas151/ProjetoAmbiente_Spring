package com.example.Projeto4_Jonas.service;

import com.example.Projeto4_Jonas.dto.EstatisticasDTO;
import com.example.Projeto4_Jonas.repository.AtividadeSustentavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EstatisticasService {
    
    @Autowired
    private AtividadeSustentavelRepository repository;
    
    public EstatisticasDTO calcularEstatisticas(LocalDateTime dataInicio, LocalDateTime dataFim) {
        Long totalAtividades = repository.count();
        
        Double impactoTotal = repository.calcularImpactoTotalPeriodo(dataInicio, dataFim);
        if (impactoTotal == null) {
            impactoTotal = 0.0;
        }
        
        if (impactoTotal == 0.0) {
            var atividades = repository.findByDataBetween(dataInicio, dataFim);
            double impactoManual = atividades.stream()
                    .mapToDouble(a -> a.getImpactoAmbiental() != null ? a.getImpactoAmbiental() : 0.0)
                    .sum();
            impactoTotal = impactoManual; 
        }
        
        Map<String, Long> atividadesPorTipo = calcularAtividadesPorTipo();
        
        long diasNoPeriodo = ChronoUnit.DAYS.between(dataInicio, dataFim) + 1;
        Double mediaImpactoDiario = diasNoPeriodo > 0 ? impactoTotal / diasNoPeriodo : 0.0;
                
        return new EstatisticasDTO(totalAtividades, impactoTotal, atividadesPorTipo, mediaImpactoDiario);
    }
    
    public EstatisticasDTO calcularEstatisticasGerais() {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime umAnoAtras = agora.minus(365, ChronoUnit.DAYS); 
        
        return calcularEstatisticas(umAnoAtras, agora);
    }
    
    public EstatisticasDTO calcularEstatisticasTodasAtividades() {
        var todasAtividades = repository.findAll();
        
        Long totalAtividades = (long) todasAtividades.size();
        Double impactoTotal = todasAtividades.stream()
                .mapToDouble(a -> a.getImpactoAmbiental() != null ? a.getImpactoAmbiental() : 0.0)
                .sum();
        
        Map<String, Long> atividadesPorTipo = calcularAtividadesPorTipo();
        Double mediaImpactoDiario = totalAtividades > 0 ? impactoTotal / totalAtividades : 0.0;
        
        return new EstatisticasDTO(totalAtividades, impactoTotal, atividadesPorTipo, mediaImpactoDiario);
    }
    
    private Map<String, Long> calcularAtividadesPorTipo() {
        Map<String, Long> resultado = new HashMap<>();
        
        List<String> tiposComuns = List.of(
            "Reciclagem", "Transporte Público", "Economia de Água", 
            "Economia de Energia", "Compostagem", "Plantio", "Reutilização"
        );
        
        for (String tipo : tiposComuns) {
            Long count = repository.contarAtividadesPorTipo(tipo);
            if (count > 0) {
                resultado.put(tipo, count);
            }
        }
        
        return resultado;
    }
    
    public Double calcularImpactoTotalDoMes() {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime inicioDoMes = agora.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime fimDoMes = agora.withDayOfMonth(agora.toLocalDate().lengthOfMonth())
                .withHour(23).withMinute(59).withSecond(59);
        
        Double impacto = repository.calcularImpactoTotalPeriodo(inicioDoMes, fimDoMes);
        return impacto != null ? impacto : 0.0;
    }
}