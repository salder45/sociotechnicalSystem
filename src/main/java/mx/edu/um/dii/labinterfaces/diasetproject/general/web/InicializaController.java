/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.general.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author laboratoriointerface
 */
@Controller
@RequestMapping("/inicializa")
public class InicializaController extends BaseController{
    
    @RequestMapping
    public String inicia(){
        log.debug("Inicia--------");
        return "inicializa/index";
    }
    
}
