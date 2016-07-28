/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.service;

import java.util.List;
import mx.edu.um.dii.labinterfaces.diasetproject.model.User;

/**
 *
 * @author laboratoriointerface
 */
public interface UserService {
    
    public User get(Long id);
    
    public User save(User user);
    
    public User update(User user);
    
    public List<User> getAll();
}
