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
import mx.edu.um.dii.labinterfaces.diasetproject.model.Customer;
import mx.edu.um.dii.labinterfaces.diasetproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author laboratoriointerface
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/new")
    public String newCustomer(Model model) {
        log.debug("Loading new customer");

        Customer customer = new Customer();
        model.addAttribute(Constants.CUSTOMER_UI, customer);

        return "/customer/new";
    }

    @RequestMapping("/create")
    public String create(HttpServletRequest request, @Valid Customer customer, BindingResult bindingResult, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        log.debug("Create customer...");

        if (bindingResult.hasErrors()) {
            log.error("Error detected in user form...");
            model.addAttribute(Constants.SELLER_UI, customer);
            return "/customer/new";
        }

        Customer c = customerService.save(customer);

        redirectAttributes.addFlashAttribute(Constants.MESSAGE_UI, "customer.created.message");
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_ATTRS_UI, new String[]{customer.getName()});

        return "redirect:/customer/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        log.debug("get List Customer");

        List<Customer> customers = customerService.getAll();

        model.addAttribute(Constants.CUSTOMER_LIST_UI, customers);

        return "/customer/list";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        log.debug("Loading edit customer");
        Customer customer = customerService.getById(id);
        log.debug(customer.toString());
        model.addAttribute(Constants.CUSTOMER_UI, customer);

        return "/customer/edit";
    }

    @Transactional
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(HttpServletRequest request, @Valid Customer customer, BindingResult bindingResult, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        log.debug("updating customer");

        if (bindingResult.hasErrors()) {
            log.error("Error detected in area form...");
            return "/customer/edit";
        }
        Customer c = customerService.update(customer);
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_UI, "customer.updated.message");
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_ATTRS_UI, new String[]{customer.getName()});

        return "redirect:/customer/show/" + customer.getId();
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        log.debug("deleting customer");
        try {
            String customerName = customerService.delete(id);
            redirectAttributes.addFlashAttribute(Constants.MESSAGE_UI,
                    "customer.deleted.message");
            redirectAttributes.addFlashAttribute(Constants.MESSAGE_ATTRS_UI,
                    new String[]{customerName});

        } catch (Exception e) {

        }
        return "redirect:/customer/list";
    }

    @RequestMapping("/show/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.debug("Show Customer...{}", id);

        Customer customer = customerService.getById(id);

        model.addAttribute(Constants.CUSTOMER_UI, customer);

        return "/customer/show";
    }

    @RequestMapping("/getCustomerList")
    @ResponseBody
    public List<Customer> getCustomerList(@RequestParam(name = "term") String filter) {
        log.debug("getCustomerList");
        return customerService.getAll();
    }
}
