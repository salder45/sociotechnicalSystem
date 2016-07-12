/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 *
 * @author laboratoriointerface
 */
@Configuration
@EnableWebSecurity
@ImportResource("classpath:security.xml")
public class SecurityConfig {
    
}
