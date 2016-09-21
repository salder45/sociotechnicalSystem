/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.service;

import java.util.List;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Customer;

/**
 *
 * @author laboratoriointerface
 */
public interface CustomerService {
    public List<Customer> getAll();
    public List<Customer> getCustomersActives();
    public List<Customer> getCustomersInactives();
    public Customer getById(Long id);
    public Customer getByName(String name);
    public Customer save(Customer customer);
    public Customer update(Customer customer);
    public String delete(Long id);
}
