/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.web;

import mx.edu.um.dii.labinterfaces.diasetproject.config.Constants;
import mx.edu.um.dii.labinterfaces.diasetproject.model.WorkOrder;
import mx.edu.um.dii.labinterfaces.diasetproject.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author eder
 */
@Controller
@RequestMapping("/workOrder")
public class WorkOrderController extends BaseController{
    @Autowired
    private WorkOrderService workOrderService;
    
    @RequestMapping("new")
    public String newWorkOrder(Model model){
        log.debug("new workOrder");
        WorkOrder workOrder=new WorkOrder();
        
        model.addAttribute(Constants.WORK_ORDER_UI, workOrder);
        
        return "/workOrder/new";
    }
}
