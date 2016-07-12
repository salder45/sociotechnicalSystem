/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.service.impl;

import mx.edu.um.dii.labinterfaces.diasetproject.model.User;
import mx.edu.um.dii.labinterfaces.diasetproject.service.BaseService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author laboratoriointerface
 */
@Service
public class UserDetailsServiceImpl extends BaseService implements UserDetailsService{

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        //@TODO still implements this
        return new User();
    }
    
}
