/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.dao;

import java.util.List;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Seller;

/**
 *
 * @author laboratoriointerface
 */
public interface SellerDao {
    public List<Seller> getSellers(Seller seller);
    public Seller get(Long id);
    public Seller get(String name);
    public Seller save(Seller seller);
    public Seller update(Seller seller);
    public String delete(Long id);
}
