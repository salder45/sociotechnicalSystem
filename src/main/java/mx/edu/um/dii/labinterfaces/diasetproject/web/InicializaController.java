/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.web;

import javax.servlet.http.HttpServletRequest;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.InicializaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author laboratoriointerface
 */
@Controller
@RequestMapping("/inicializa")
public class InicializaController extends BaseController{
    
    @Autowired
    private InicializaDao inicializaDao;   
    
    @RequestMapping
    public String inicia(){
        log.debug("Inicia--------");
        return "inicializa/index";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String save(HttpServletRequest request, @RequestParam String username, @RequestParam String password){
        log.debug("Creating Users...");
        log.debug("User:{} ", username);
        log.debug("Password:{} ", password);
        
        inicializaDao.inicializa(username, password);
        
        return "redirect:/";
    }
    
}
