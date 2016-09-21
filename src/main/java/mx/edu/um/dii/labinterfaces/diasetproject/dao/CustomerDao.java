/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.dao;

import java.util.List;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Customer;

/**
 *
 * @author laboratoriointerface
 */
public interface CustomerDao {
    public List<Customer> getCustomers(Customer customer);
    public Customer get(Long id);
    public Customer get(String name);
    public Customer save(Customer customer);
    public Customer update(Customer customer);
    public String delete(Long id);
    
}
