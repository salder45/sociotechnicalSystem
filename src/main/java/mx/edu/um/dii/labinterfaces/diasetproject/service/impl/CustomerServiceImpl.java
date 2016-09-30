/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.service.impl;

import java.util.List;
import mx.edu.um.dii.labinterfaces.diasetproject.config.Constants;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.CustomerDao;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Customer;
import mx.edu.um.dii.labinterfaces.diasetproject.service.BaseService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author laboratoriointerface
 */
@Service
public class CustomerServiceImpl extends BaseService implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public List<Customer> getAll() {
        return customerDao.getCustomers(new Customer());
    }
    
    @Override
    public List<Customer> getAutcompleteNameAndCode(String filter) {
        filter="%"+filter+"%";
        Customer customer=new Customer();
        customer.setName(filter);
        return customerDao.getCustomers(customer);
    }

    @Override
    public List<Customer> getCustomersActives() {
        Customer customer=new Customer();
        customer.setStatus(Constants.STATUS_ACTIVE);
        
        return customerDao.getCustomers(customer);
    }

    @Override
    public List<Customer> getCustomersInactives() {
        Customer customer=new Customer();
        customer.setStatus(Constants.STATUS_INACTIVE);
        
        return customerDao.getCustomers(customer);
    }

    @Override
    public Customer getById(Long id) {
        return customerDao.get(id);
    }

    @Override
    public Customer getByName(String name) {
        return customerDao.get(name);
    }

    @Override
    public Customer save(Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        return customerDao.update(customer);
    }

    @Override
    public String delete(Long id) {
        return customerDao.delete(id);
    }    

}
