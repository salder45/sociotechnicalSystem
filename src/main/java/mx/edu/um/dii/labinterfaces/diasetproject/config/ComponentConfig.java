/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 *
 * @author laboratoriointerface
 */
@Configuration
@ComponentScan(basePackages = "mx.edu.um.dii.labinterfaces.diasetproject")
@PropertySource("file:${user.home}/.diaset.properties")

public class ComponentConfig {

}
