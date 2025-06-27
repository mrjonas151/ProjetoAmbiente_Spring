package com.example.Projeto4_Jonas.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    
    private final JavaMailSender mailSender;
    
    @Value("${app.email.sender-name:Topaz Environment}")
    private String senderName;
    
    @Value("${app.email.from-name:Topaz Contatos}")
    private String fromName;
    
    @Value("${app.email.from-email:hello@demomailtrap.co}")
    private String fromEmail;
    
    @Value("${app.email.default-subject:Impactos Ambientais e sua importancia}")
    private String defaultSubject;
    
    @Value("${app.email.mock.enabled:false}")
    private boolean mockEnabled;
    
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    
    public void enviarRelatorioSustentavel(String destinatario, String mensagem) {
        if (mockEnabled) {
            enviarEmailMock(destinatario, defaultSubject, mensagem);
            return;
        }
        
        try {
            
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            
            helper.setFrom("\"" + fromName + "\" <" + fromEmail + ">");
            helper.setTo(destinatario);
            helper.setSubject(defaultSubject + " - via " + fromName);
            helper.setText(criarEmailHTML(mensagem), true);
            
            mailSender.send(mimeMessage);
            
            
        } catch (Exception e) {
            logger.error("Erro ao enviar relatório via Mailtrap Live para {}: {}", destinatario, e.getMessage());
            throw new RuntimeException("Erro ao enviar email: " + e.getMessage());
        }
    }
    
    public void enviarEmailTeste(String destinatario) {
        if (mockEnabled) {
            enviarEmailMock(destinatario, "Teste - " + defaultSubject, "Email de teste do sistema!");
            return;
        }
        
        try {
            
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            
            helper.setFrom("\"" + fromName + "\" <" + fromEmail + ">");
            helper.setTo(destinatario);
            helper.setSubject("Teste - " + defaultSubject + " - via " + fromName);
            helper.setText(criarEmailTesteHTML(), true); 
            
            mailSender.send(mimeMessage);
            
            
        } catch (Exception e) {
            logger.error("Erro ao enviar email de teste via Mailtrap Live para {}: {}", destinatario, e.getMessage());
            throw new RuntimeException("Erro ao enviar email de teste: " + e.getMessage());
        }
    }
    
    private String criarEmailHTML(String relatorio) {
        return """
            <html>
            <body style="font-family: Arial, sans-serif; line-height: 1.6; color: #333;">
                <div style="max-width: 600px; margin: 0 auto; padding: 20px;">
                    <h2 style="color: #2e7d32;">🌱 %s</h2>
                    <p><strong>Enviado por:</strong> %s</p>
                    
                    <div style="background-color: #f1f8e9; padding: 20px; border-radius: 8px; margin: 20px 0;">
                        <pre style="white-space: pre-wrap; font-family: Arial, sans-serif; margin: 0;">%s</pre>
                    </div>
                    
                    <div style="background-color: #e8f5e8; padding: 15px; border-radius: 8px; margin-top: 20px;">
                        <p style="margin: 0;"><strong>🌍 Continue fazendo a diferença!</strong></p>
                        <p style="margin: 5px 0 0 0;">Cada ação sustentável conta para um planeta melhor! 🌿</p>
                    </div>
                    
                    <hr style="margin: 30px 0; border: none; border-top: 1px solid #ddd;">
                    <small style="color: #666;">
                        Esta mensagem foi enviada através da plataforma %s<br>
                        Sistema de Monitoramento de Atividades Sustentáveis
                    </small>
                </div>
            </body>
            </html>
            """.formatted(defaultSubject, senderName, relatorio, fromName);
    }
    
    private String criarEmailTesteHTML() {
        return """
            <html>
            <body style="font-family: Arial, sans-serif; line-height: 1.6; color: #333;">
                <div style="max-width: 600px; margin: 0 auto; padding: 20px;">
                    <h2 style="color: #2e7d32;">🌱 Teste do Sistema de Email</h2>
                    <p><strong>Enviado por:</strong> %s</p>
                    
                    <div style="background-color: #e3f2fd; padding: 20px; border-radius: 8px; margin: 20px 0;">
                        <p><strong>✅ Configuração do Mailtrap Live funcionando perfeitamente!</strong></p>
                        
                        <h4>Detalhes técnicos:</h4>
                        <ul>
                            <li><strong>Servidor:</strong> live.smtp.mailtrap.io</li>
                            <li><strong>Porta:</strong> 587</li>
                            <li><strong>Username:</strong> api</li>
                            <li><strong>Remetente:</strong> %s</li>
                            <li><strong>Email:</strong> %s</li>
                        </ul>
                        
                        <p>Se você recebeu este email, tudo está configurado corretamente! 🎉</p>
                    </div>
                    
                    <div style="background-color: #f3e5f5; padding: 15px; border-radius: 8px; margin-top: 20px;">
                        <h4 style="margin-top: 0;">📋 Próximos passos:</h4>
                        <ul>
                            <li>✅ Testar envio de relatórios</li>
                            <li>🔗 Integrar com o frontend</li>
                            <li>📊 Verificar emails no painel do Mailtrap</li>
                        </ul>
                    </div>
                    
                    <hr style="margin: 30px 0; border: none; border-top: 1px solid #ddd;">
                    <small style="color: #666;">
                        Esta mensagem foi enviada através da plataforma %s<br>
                        Sistema %s
                    </small>
                </div>
            </body>
            </html>
            """.formatted(senderName, fromName, fromEmail, fromName, senderName);
    }
    
}