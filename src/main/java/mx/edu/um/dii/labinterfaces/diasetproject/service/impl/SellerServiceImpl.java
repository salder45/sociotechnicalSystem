/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.service.impl;

import java.util.List;
import mx.edu.um.dii.labinterfaces.diasetproject.config.Constants;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.SellerDao;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Seller;
import mx.edu.um.dii.labinterfaces.diasetproject.service.BaseService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author laboratoriointerface
 */
@Service
public class SellerServiceImpl extends BaseService implements SellerService {

    @Autowired
    private SellerDao sellerDao;

    @Override
    public List<Seller> getAll() {
        return sellerDao.getSellers(new Seller());
    }

    @Override
    public List<Seller> getAutcompleteNameAndCode(String filter) {
        filter="%"+filter+"%";
        Seller seller=new Seller();
        seller.setName(filter);
        return sellerDao.getSellers(seller);
    }

    @Override
    public List<Seller> getSellersActives() {
        Seller seller = new Seller();
        seller.setStatus(Constants.STATUS_ACTIVE);
        return sellerDao.getSellers(seller);
    }

    @Override
    public List<Seller> getSellersInactives() {
        Seller seller = new Seller();
        seller.setStatus(Constants.STATUS_INACTIVE);
        return sellerDao.getSellers(seller);
    }

    @Override
    public Seller getById(Long id) {
        return sellerDao.get(id);
    }

    @Override
    public Seller getByName(String name) {
        return sellerDao.get(name);
    }

    @Override
    public Seller save(Seller seller) {
        return sellerDao.save(seller);
    }

    @Override
    public Seller update(Seller seller) {
        return sellerDao.update(seller);
    }

    @Override
    public String delete(Long id) {
        return sellerDao.delete(id);
    }

}
