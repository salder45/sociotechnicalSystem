/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author laboratoriointerface
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
    
    @RequestMapping("/perfil")
    public String getPerfil(){
        return "user/edit";
    }
    
}
