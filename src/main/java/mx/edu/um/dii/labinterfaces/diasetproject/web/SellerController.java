/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import mx.edu.um.dii.labinterfaces.diasetproject.config.Constants;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Seller;
import mx.edu.um.dii.labinterfaces.diasetproject.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @RequestMapping("/create")
    public String create(HttpServletRequest request, @Valid Seller seller, BindingResult bindingResult, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        log.debug("Create sellers...");

        if (bindingResult.hasErrors()) {
            log.error("Error detected in user form...");
            model.addAttribute(Constants.SELLER_UI, seller);
            return "/seller/new";
        }

        Seller s = sellerService.save(seller);

        redirectAttributes.addFlashAttribute(Constants.MESSAGE_UI, "seller.created.message");
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_ATTRS_UI, new String[]{seller.getName()});

        return "redirect:/seller/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        log.debug("get List Seller");

        List<Seller> sellerList = sellerService.getAll();

        model.addAttribute(Constants.SELLER_LIST_UI, sellerList);

        return "/seller/list";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Seller seller = sellerService.getById(id);

        model.addAttribute(Constants.SELLER_UI, seller);

        return "/seller/edit";
    }

    @Transactional
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(HttpServletRequest request, @Valid Seller seller, BindingResult bindingResult, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        log.debug("loading edit screen");

        if (bindingResult.hasErrors()) {
            log.error("Error detected in area form...");
            return "/seller/edit";
        }

        Seller s = sellerService.update(seller);
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_UI, "seller.updated.message");
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_ATTRS_UI, new String[]{s.getName()});

        return "redirect:/seller/show/" + seller.getId();
    }
    
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        log.debug("delete seller");
        try{
            String sellerName=sellerService.delete(id);
            redirectAttributes.addFlashAttribute(Constants.MESSAGE_UI,
                    "seller.deleted.message");
            redirectAttributes.addFlashAttribute(Constants.MESSAGE_ATTRS_UI,
                    new String[]{sellerName});
        }catch(Exception e){
        
        }
        
        return "redirect:/seller/list";
    }
    
    @RequestMapping("/show/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.debug("Show Seller...{}", id);
        
        Seller seller=sellerService.getById(id);
        model.addAttribute(Constants.SELLER_UI, seller);
        
        return "/seller/show";
    }
}
