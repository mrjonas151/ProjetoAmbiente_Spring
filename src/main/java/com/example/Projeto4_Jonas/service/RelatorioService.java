package com.example.Projeto4_Jonas.service;

import com.example.Projeto4_Jonas.dto.AtividadeSustentavelDTO;
import com.example.Projeto4_Jonas.dto.RelatorioDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RelatorioService {
    
    private static final Logger logger = LoggerFactory.getLogger(RelatorioService.class);
    
    @Autowired
    private AtividadeSustentavelService atividadeService;
    
    @Autowired
    private OpenAIService openAIService;
    
    public RelatorioDTO gerarRelatorioPersonalizado() {
        List<AtividadeSustentavelDTO> todasAtividades = atividadeService.listarTodas();
        
        if (todasAtividades.isEmpty()) {
            throw new RuntimeException("Nenhuma atividade sustentável registrada. " +
                "Registre algumas atividades primeiro para gerar um relatório personalizado.");
        }
        
        try {
            String promptParaIA = construirPrompt(todasAtividades);
            String relatorioIA = openAIService.gerarRelatorio(promptParaIA);
            
            return new RelatorioDTO(
                relatorioIA,
                (long) todasAtividades.size(),
                "Análise de " + todasAtividades.size() + " atividades registradas"
            );
            
        } catch (Exception e) {
            logger.error("Erro ao gerar relatório: {}", e.getMessage());
            throw new RuntimeException("Erro ao gerar relatório com IA: " + e.getMessage());
        }
    }
    
    public RelatorioDTO gerarRelatorioPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) {
        List<AtividadeSustentavelDTO> atividades = atividadeService.buscarPorPeriodo(dataInicio, dataFim);
        
        if (atividades.isEmpty()) {
            throw new RuntimeException("Nenhuma atividade sustentável encontrada no período " + 
                formatarPeriodo(dataInicio, dataFim) + ". " +
                "Registre atividades neste período para gerar um relatório.");
        }
        
        try {
            String promptParaIA = construirPromptComPeriodo(atividades, dataInicio, dataFim);
            String relatorioIA = openAIService.gerarRelatorio(promptParaIA);
            
            return new RelatorioDTO(
                relatorioIA,
                (long) atividades.size(),
                formatarPeriodo(dataInicio, dataFim)
            );
            
        } catch (Exception e) {
            logger.error("Erro ao gerar relatório por período: {}", e.getMessage());
            throw new RuntimeException("Erro ao gerar relatório com IA: " + e.getMessage());
        }
    }
    
    private String construirPrompt(List<AtividadeSustentavelDTO> atividades) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Analise estas atividades sustentáveis e gere um relatório motivador:\n\n");
        
        Map<String, List<AtividadeSustentavelDTO>> atividadesPorTipo = atividades.stream()
            .collect(Collectors.groupingBy(AtividadeSustentavelDTO::getTipo));
        
        for (Map.Entry<String, List<AtividadeSustentavelDTO>> entry : atividadesPorTipo.entrySet()) {
            String tipo = entry.getKey();
            List<AtividadeSustentavelDTO> atividadesDoTipo = entry.getValue();
            
            prompt.append("🌱 ").append(tipo).append(" (").append(atividadesDoTipo.size()).append(" atividades):\n");
            
            atividadesDoTipo.forEach(atividade -> {
                prompt.append("  • ").append(atividade.getDescricao());
                if (atividade.getImpactoAmbiental() != null && atividade.getImpactoAmbiental() > 0) {
                    prompt.append(" (").append(atividade.getImpactoAmbiental());
                    if (atividade.getUnidadeImpacto() != null) {
                        prompt.append(" ").append(atividade.getUnidadeImpacto());
                    }
                    prompt.append(")");
                }
                prompt.append("\n");
            });
            prompt.append("\n");
        }
        
        prompt.append("Instruções para o relatório:\n");
        prompt.append("1. Parabenize o usuário de forma específica pelas atividades\n");
        prompt.append("2. Destaque os impactos positivos calculados\n");
        prompt.append("3. Sugira 2-3 novas atividades sustentáveis práticas para o Brasil\n");
        prompt.append("4. Use tom motivador e pessoal\n");
        prompt.append("5. Inclua emojis e seja conciso\n");
        
        return prompt.toString();
    }
    
    private String construirPromptComPeriodo(List<AtividadeSustentavelDTO> atividades, 
                                           LocalDateTime dataInicio, LocalDateTime dataFim) {
        String prompt = construirPrompt(atividades);
        prompt += "\nPeríodo analisado: " + formatarPeriodo(dataInicio, dataFim) + "\n";
        
        return prompt;
    }
    
    private String formatarPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataInicio.format(formatter) + " a " + dataFim.format(formatter);
    }
}