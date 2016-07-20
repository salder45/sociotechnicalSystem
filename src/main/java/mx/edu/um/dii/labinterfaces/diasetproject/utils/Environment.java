/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.utils;

import javax.servlet.http.HttpSession;
import mx.edu.um.dii.labinterfaces.diasetproject.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author laboratoriointerface
 */
@Component
public class Environment {
    private static final Logger log=LoggerFactory.getLogger(Environment.class);
    
    public void updateSession(HttpSession session){
    }
    
    public void updateSession(HttpSession session,User user){
        //@TODO
    }
    
    public User getUser(){
            User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return user;
    }
}
