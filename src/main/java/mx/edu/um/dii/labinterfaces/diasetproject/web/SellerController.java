/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.web;

import mx.edu.um.dii.labinterfaces.diasetproject.config.Constants;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Seller;
import mx.edu.um.dii.labinterfaces.diasetproject.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author laboratoriointerface
 */
@Controller
@RequestMapping("/seller")
public class SellerController extends BaseController {

    @Autowired
    private SellerService sellerService;

    @RequestMapping("/new")
    public String newSeller(Model model) {
        log.debug("loading new screen");
        Seller seller = new Seller();
        
        model.addAttribute(Constants.SELLER_UI, seller);

        return "/seller/new";
    }
}
