/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.dao;

import mx.edu.um.dii.labinterfaces.diasetproject.model.Role;

/**
 *
 * @author laboratoriointerface
 */
public interface RoleDao {
    
    public Role get(Long id);
    
    public Role get(String authority);
    
    public Role save(Role role);
    
}
