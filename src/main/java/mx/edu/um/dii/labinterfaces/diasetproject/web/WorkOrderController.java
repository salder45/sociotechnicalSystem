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
import mx.edu.um.dii.labinterfaces.diasetproject.model.Area;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Batch;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Customer;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Seller;
import mx.edu.um.dii.labinterfaces.diasetproject.model.WorkOrder;
import mx.edu.um.dii.labinterfaces.diasetproject.service.AreaService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.CustomerService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.SellerService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @Autowired
    private AreaService areaService;

    @RequestMapping("new")
    public String newWorkOrder(Model model) {
        log.debug("new workOrder");
        WorkOrder workOrder = new WorkOrder();
        //set select data
        List<Seller> sellersList = sellerService.getAll();
        List<Customer> customerList = customerService.getAll();
        //
        model.addAttribute(Constants.WORK_ORDER_UI, workOrder);
        model.addAttribute(Constants.SELLER_LIST_UI, sellersList);
        model.addAttribute(Constants.CUSTOMER_LIST_UI, customerList);

        return "/workOrder/new";
    }

    @RequestMapping("/create")
    public String create(HttpServletRequest request, @Valid WorkOrder workOrder, BindingResult bindingResult, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        log.debug("create workOrder");
        if (bindingResult.hasErrors()) {
            log.error("Error detected in work form...");
            model.addAttribute(Constants.WORK_ORDER_UI, workOrder);
            return "/workOrder/new";
        }

        WorkOrder w = workOrderService.create(workOrder);

        redirectAttributes.addFlashAttribute(Constants.MESSAGE_UI, "workorder.created.message");
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_ATTRS_UI, new String[]{workOrder.getCode()});

        return "redirect:/workOrder/edit" + w.getId();
    }
    
    @RequestMapping("/listOrders/{areaId}")
    public String listSellingOrders(@PathVariable Long areaId, Model model){
        //Area
        Area area=areaService.get(areaId);
        WorkOrder order=new WorkOrder();
        order.setAreaActual(area);
        //
        List<WorkOrder> workOrdersList;
        
        return "/workOrder/list";
    }

    @RequestMapping("/addBatch")
    public String addBatch(HttpServletRequest request, @Valid Batch batch, BindingResult bindingResult, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        log.debug("addBatchs");
        
        return "";
    }
}
