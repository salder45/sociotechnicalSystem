/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.service;

import java.util.List;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Seller;

/**
 *
 * @author laboratoriointerface
 */
public interface SellerService {
    public List<Seller> getAll();
    public List<Seller> getAutcompleteNameAndCode(String filter);
    public List<Seller> getSellersActives();
    public List<Seller> getSellersInactives();
    public Seller getById(Long id);
    public Seller getByName(String name);
    public Seller save(Seller seller);
    public Seller update(Seller seller);
    public String delete(Long id);
}
