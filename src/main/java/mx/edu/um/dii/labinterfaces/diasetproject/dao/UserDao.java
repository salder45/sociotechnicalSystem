/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.dao;

import java.util.List;
import mx.edu.um.dii.labinterfaces.diasetproject.model.User;

/**
 *
 * @author laboratoriointerface
 */
public interface UserDao {
    public List<User> get();
    public User get(Long id);
    public User get(String username);
    public User save(User user);
    public User update(User user);
    public String delete(Long id);
    
}
