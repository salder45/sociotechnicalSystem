/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import mx.edu.um.dii.labinterfaces.diasetproject.config.Constants;
import mx.edu.um.dii.labinterfaces.diasetproject.model.WorkOrder;
import mx.edu.um.dii.labinterfaces.diasetproject.service.CustomerService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.SellerService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author eder
 */
@Controller
@RequestMapping("/workOrder")
public class WorkOrderController extends BaseController {

    @Autowired
    private WorkOrderService workOrderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SellerService sellerService;

    @RequestMapping("new")
    public String newWorkOrder(Model model) {
        log.debug("new workOrder");
        WorkOrder workOrder = new WorkOrder();

        model.addAttribute(Constants.WORK_ORDER_UI, workOrder);

        return "/workOrder/new";
    }

    @RequestMapping("/create")
    public String create(HttpServletRequest request, @Valid WorkOrder workOrder, BindingResult bindingResult, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        log.debug("create workOrder");
        if (bindingResult.hasErrors()) {
            log.error("Error detected in user form...");
            model.addAttribute(Constants.WORK_ORDER_UI, workOrder);
            return "/workOrder/new";
        }

        WorkOrder w = workOrderService.save(workOrder);

        redirectAttributes.addFlashAttribute(Constants.MESSAGE_UI, "workorder.created.message");
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_ATTRS_UI, new String[]{workOrder.getCode()});

        return "redirect:/workOrder/list";
    }
}