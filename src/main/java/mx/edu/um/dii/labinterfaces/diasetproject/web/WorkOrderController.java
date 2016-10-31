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
import mx.edu.um.dii.labinterfaces.diasetproject.model.Machine;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Seller;
import mx.edu.um.dii.labinterfaces.diasetproject.model.WorkOrder;
import mx.edu.um.dii.labinterfaces.diasetproject.service.AreaService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.BatchService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.CustomerService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.MachineService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.SellerService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.WorkOrderService;
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

    @Autowired
    private BatchService batchService;

    @Autowired
    private MachineService machineService;

    @RequestMapping("/new")
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

        return "redirect:/workOrder/addWorkOrderDetails/" + w.getId();
    }

    @RequestMapping("/close/{id}")
    public String close(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        WorkOrder workOrder = workOrderService.getById(id);
        Long areaId = workOrder.getAreaActual().getId();

        workOrderService.close(workOrder);

        redirectAttributes.addFlashAttribute(Constants.MESSAGE_UI, "workorder.closed.message");
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_ATTRS_UI, new String[]{workOrder.getCode()});

        return "redirect:/workOrder/listOrders/" + areaId;
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        log.debug("edit WorkOrder...");
        WorkOrder workOrder = workOrderService.getById(id);
        //set select data
        List<Seller> sellersList = sellerService.getAll();
        List<Customer> customerList = customerService.getAll();
        //
        model.addAttribute(Constants.WORK_ORDER_UI, workOrder);
        model.addAttribute(Constants.SELLER_LIST_UI, sellersList);
        model.addAttribute(Constants.CUSTOMER_LIST_UI, customerList);
        //
        return "/workOrder/edit";
    }

    @Transactional
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(HttpServletRequest request, @Valid WorkOrder workOrder, BindingResult bindingResult, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        log.debug("Update workOrder...");
        if (bindingResult.hasErrors()) {
            log.error("Error detected in workOrder form...");
            return "/workOrder/edit";
        }
        WorkOrder w = workOrderService.update(workOrder);

        redirectAttributes.addFlashAttribute(Constants.MESSAGE_UI,
                "workorder.updated.message");
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_ATTRS_UI,
                new String[]{w.getCode()});
        //
        return "redirect:/workOrder/addWorkOrderDetails/" + workOrder.getId();
    }

    @RequestMapping("/loadEstimatedReleaseDate/{id}")
    public String loadEstimatedReleaseDate(@PathVariable Long id, Model model) {
        log.debug("loadEstimatedReleaseDate WorkOrder...");
        WorkOrder workOrder = workOrderService.getById(id);
        //
        model.addAttribute(Constants.WORK_ORDER_UI, workOrder);
        return "/workOrder/setReleaseDate";
    }

    @Transactional
    @RequestMapping(value = "/setEstimatedReleaseDate", method = RequestMethod.POST)
    public String setEstimatedReleaseDate(HttpServletRequest request, @Valid WorkOrder workOrder, BindingResult bindingResult, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        log.debug("Set Release Date...");
        if (bindingResult.hasErrors()) {
            log.error("Error detected in workOrder form...");
            model.addAttribute(Constants.WORK_ORDER_UI, workOrder);

            return "/workOrder/setReleaseDate";
        }

        workOrder = workOrderService.setEstimatedReleaseDate(workOrder.getEstimatedReleaseDate(), workOrder.getId());
        //set message
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_UI,
                "workorder.updated.message");
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_ATTRS_UI,
                new String[]{workOrder.getCode()});
        //
        return "redirect:/workOrder/listOrders/" + workOrder.getAreaActual().getId();
    }

    @RequestMapping("/sendWorkOrderToArea/{id}")
    public String loadSendToAnotherArea(@PathVariable Long id, Model model) {
        log.debug("loadSendToAnotherArea WorkOrder...");
        WorkOrder workOrder = workOrderService.getById(id);
        List<Area> areasList = areaService.getAll();
        //
        model.addAttribute(Constants.WORK_ORDER_UI, workOrder);
        model.addAttribute(Constants.AREA_LIST_UI, areasList);

        return "/workOrder/sendToArea";
    }

    @Transactional
    @RequestMapping(value = "/sendWorkOrder", method = RequestMethod.POST)
    public String sendWorkOrder(HttpServletRequest request, @Valid WorkOrder workOrder, BindingResult bindingResult, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        log.debug("Send Work Order...");
        //scrap
        Integer scrap = workOrder.getBadPieces();
        WorkOrder w = workOrderService.getById(workOrder.getId());
        Long areaId = w.getAreaActual().getId();
        if (bindingResult.hasErrors()) {
            log.error("Error detected in workOrder form...");
            List<Area> areasList = areaService.getAll();
            //
            model.addAttribute(Constants.WORK_ORDER_UI, workOrder);
            model.addAttribute(Constants.AREA_LIST_UI, areasList);
            return "/workOrder/sendToArea";
        }
        workOrder = workOrderService.sendToArea(workOrder.getAreaActual().getId(), workOrder.getId(), scrap);
        //messagesHere
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_UI,
                "workorder.sent.message");
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_ATTRS_UI,
                new String[]{workOrder.getCode()});

        return "redirect:/workOrder/listOrders/" + areaId;
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        log.debug("delete WorkOrder...");
        try {
            String orderCode = workOrderService.delete(id);
            redirectAttributes.addFlashAttribute(Constants.MESSAGE_UI,
                    "workorder.deleted.message");
            redirectAttributes.addFlashAttribute(Constants.MESSAGE_ATTRS_UI,
                    new String[]{orderCode});
            log.debug("User: {} deleted", orderCode);
        } catch (Exception e) {
        }
        //TODO check this redirect
        return "redirect:/workOrder/new";
    }

    @RequestMapping("/listOrders/{areaId}")
    public String listOrders(@PathVariable Long areaId, Model model) {
        //Area
        Area area = areaService.get(areaId);
        WorkOrder order = new WorkOrder();
        order.setAreaActual(area);
        //
        List<WorkOrder> workOrdersList = workOrderService.getByArea(areaId);
        model.addAttribute(Constants.WORK_ORDER_LIST_UI, workOrdersList);
        model.addAttribute(Constants.AREA_UI, area);

        return "/workOrder/list";
    }

    @RequestMapping("/addBatch")
    public String addBatch(HttpServletRequest request, @Valid Batch batch, BindingResult bindingResult, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        log.debug("addBatchs");
        //
        WorkOrder workOrder = workOrderService.getById(batch.getWorkOrder().getId());
        if (bindingResult.hasErrors()) {
            log.error("Error detected in batch form...");
            model.addAttribute(Constants.WORK_ORDER_UI, workOrder);
            model.addAttribute(Constants.BATCH_UI, batch);
            return "/workOrder/details";
        }
        //save batch
        batch.setWorkOrder(workOrder);
        batchService.save(batch);
        //
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_UI, "batch.created.message");
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_ATTRS_UI, new String[]{batch.getDescription()});
        //redirect to addWorkOrderDetails
        return "redirect:/workOrder/addWorkOrderDetails/" + batch.getWorkOrder().getId();
    }

    @RequestMapping("/removeBatch/{batchId}")
    public String removeBatch(@PathVariable Long batchId, Model model, RedirectAttributes redirectAttributes) {
        log.debug("delete Batchs...");
        Batch batch = batchService.getById(batchId);
        try {
            String batchDescription = batchService.delete(batchId);
            redirectAttributes.addFlashAttribute(Constants.MESSAGE_UI,
                    "batch.deleted.message");
            redirectAttributes.addFlashAttribute(Constants.MESSAGE_ATTRS_UI,
                    new String[]{batchDescription});
            log.debug("Batch: {} deleted", batchDescription);
        } catch (Exception e) {
        }

        redirectAttributes.addFlashAttribute(Constants.MESSAGE_UI, "batch.deleted.message");
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_ATTRS_UI, new String[]{batch.getDescription()});

        return "redirect:/workOrder/addWorkOrderDetails/" + batch.getWorkOrder().getId();
    }

    @RequestMapping("/editBatch/{batchId}")
    public String editBatch(@PathVariable Long batchId, Model model) {
        log.debug("edit Batch...");
        Batch batch = batchService.getById(batchId);

        model.addAttribute(Constants.WORK_ORDER_UI, batch.getWorkOrder());
        model.addAttribute(Constants.BATCH_UI, batch);

        return "/workOrder/details";
    }

    @RequestMapping("/updateBatch")
    public String updateBatch(HttpServletRequest request, @Valid Batch batch, BindingResult bindingResult, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        log.debug("addBatchs");
        //
        WorkOrder workOrder = workOrderService.getById(batch.getWorkOrder().getId());
        if (bindingResult.hasErrors()) {
            log.error("Error detected in batch form...");
            model.addAttribute(Constants.WORK_ORDER_UI, workOrder);
            model.addAttribute(Constants.BATCH_UI, batch);
            return "/workOrder/details";
        }

        //save batch
        batch.setWorkOrder(workOrder);
        batchService.update(batch);

        redirectAttributes.addFlashAttribute(Constants.MESSAGE_UI, "batch.updated.message");
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_ATTRS_UI, new String[]{batch.getDescription()});

        //redirect to addWorkOrderDetails
        return "redirect:/workOrder/addWorkOrderDetails/" + batch.getWorkOrder().getId();
    }

    @RequestMapping("/addWorkOrderDetails/{id}")
    public String addWorkOrderDetails(@PathVariable Long id, Model model) {
        log.debug("addBatchs");

        WorkOrder workOrder = workOrderService.getById(id);
        Batch batch = new Batch();
        batch.setWorkOrder(workOrder);

        model.addAttribute(Constants.WORK_ORDER_UI, workOrder);
        model.addAttribute(Constants.BATCH_UI, batch);

        return "/workOrder/details";
    }

    @RequestMapping("/selectMachineToPutIn/{id}")
    public String selectMachineToPutIn(@PathVariable Long id, Model model) {
        log.debug("selectMachineToPutIn");
        WorkOrder workOrder = workOrderService.getById(id);
        List<Machine> machineList = machineService.getByArea(workOrder.getAreaActual().getId());
        //getBatchList
        List<Batch> batchsList = batchService.getBatchsListByWorkOrderAndStatus(id, Constants.STATUS_ACTIVE);

        model.addAttribute(Constants.WORK_ORDER_UI, workOrder);
        model.addAttribute(Constants.MACHINE_LIST_UI, machineList);
        model.addAttribute(Constants.BATCH_LIST_UI, batchsList);
        model.addAttribute(Constants.ORIGIN, Constants.ORIGIN_PUT);

        return "/workOrder/selectMachine";
    }

    @RequestMapping("/putInMachine")
    public String putInMachine(HttpServletRequest request, @Valid WorkOrder workOrder, BindingResult bindingResult, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        log.debug("putInMachine Work Order...");
        WorkOrder w = workOrderService.getById(workOrder.getId());
        if (bindingResult.hasErrors()) {
            log.error("Error detected in workOrder form...");
            List<Machine> machineList = machineService.getByArea(workOrder.getAreaActual().getId());
            List<Batch> batchsList = batchService.getBatchsListByWorkOrderAndStatus(workOrder.getId(), Constants.STATUS_ACTIVE);

            model.addAttribute(Constants.WORK_ORDER_UI, workOrder);
            model.addAttribute(Constants.MACHINE_LIST_UI, machineList);
            model.addAttribute(Constants.BATCH_LIST_UI, batchsList);
            model.addAttribute(Constants.ORIGIN, Constants.ORIGIN_PUT);

            return "/workOrder/selectMachine";
        }

        workOrderService.setToMachine(workOrder.getMachineActual().getId(), workOrder.getId(), workOrder.getBatch().getId());
        //
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_UI, "workorder.process.message");
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_ATTRS_UI, new String[]{w.getCode()});
        //        
        return "redirect:/workOrder/listOrders/" + w.getAreaActual().getId();
    }

    @RequestMapping("/selectMachineToPullOut/{id}")
    public String selectMachineToPullOut(@PathVariable Long id, Model model) {
        log.debug("pullOutMachine Work Order...");

        WorkOrder workOrder = workOrderService.getById(id);

        List<Machine> machineList = machineService.getByArea(workOrder.getAreaActual().getId());
        //getBatchList
        List<Batch> batchsList = batchService.getBatchsListByWorkOrderAndStatus(id, Constants.STATUS_WORKING_AT);

        model.addAttribute(Constants.WORK_ORDER_UI, workOrder);
        model.addAttribute(Constants.MACHINE_LIST_UI, machineList);
        model.addAttribute(Constants.BATCH_LIST_UI, batchsList);
        model.addAttribute(Constants.ORIGIN, Constants.ORIGIN_PULL);

        return "/workOrder/selectMachine";
    }

    @RequestMapping("/pullOutMachine")
    public String pullOutMachine(HttpServletRequest request, @Valid WorkOrder workOrder, BindingResult bindingResult, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        log.debug("pullOutMachine Work Order...");
        WorkOrder w = workOrderService.getById(workOrder.getId());
        if (bindingResult.hasErrors()) {
            log.error("Error detected in workOrder form...");
            List<Machine> machineList = machineService.getByArea(workOrder.getAreaActual().getId());
            List<Batch> batchsList = batchService.getBatchsListByWorkOrderAndStatus(workOrder.getId(), Constants.STATUS_ACTIVE);

            model.addAttribute(Constants.WORK_ORDER_UI, w);
            model.addAttribute(Constants.MACHINE_LIST_UI, machineList);
            model.addAttribute(Constants.BATCH_LIST_UI, batchsList);
            model.addAttribute(Constants.ORIGIN, Constants.ORIGIN_PULL);

            return "/workOrder/selectMachine";
        }

        workOrderService.pullOutMachine(w.getMachineActual().getId(), w.getId(), workOrder.getBatch().getId(), workOrder.getBadPieces());
        log.debug("Pass save data");
        //
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_UI, "workorder.stopped.message");
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_ATTRS_UI, new String[]{workOrder.getCode()});
        log.debug("Pass attribute redirect");

        return "redirect:/workOrder/listOrders/" + w.getAreaActual().getId();
    }
}
