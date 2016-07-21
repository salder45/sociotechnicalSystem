/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.web;

import mx.edu.um.dii.labinterfaces.diasetproject.config.Constants;
import mx.edu.um.dii.labinterfaces.diasetproject.model.User;
import mx.edu.um.dii.labinterfaces.diasetproject.utils.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author laboratoriointerface
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
    
    @Autowired
    private Environment enviroment;
    
    @RequestMapping("/perfil")
    public String getPerfil(Model model){
        User user=enviroment.getUser();
        model.addAttribute(Constants.USER_UI, user);
        return "user/edit";
    }
    
}
