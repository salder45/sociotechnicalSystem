/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 *
 * @author laboratoriointerface
 */
@Configuration
@Import(PropertyPlaceholderConfig.class)
public class MailConfig {
    @Value("${mail.smtp.host}")
    private String mailHost;
    @Value("${mail.smtp.port}")
    private String mailPort;
    
    @Bean
    public JavaMailSender mailSender(){
        JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
        mailSender.setDefaultEncoding(Constants.DEFAULT_ENCODING);
        mailSender.setHost(mailHost);
        mailSender.setPort(new Integer(mailPort));
        return mailSender;
    }
}
