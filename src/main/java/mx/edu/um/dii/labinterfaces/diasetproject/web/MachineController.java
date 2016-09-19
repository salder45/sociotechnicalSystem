/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import mx.edu.um.dii.labinterfaces.diasetproject.config.Constants;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Area;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Machine;
import mx.edu.um.dii.labinterfaces.diasetproject.service.AreaService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author laboratoriointerface
 */
@Controller
@RequestMapping("/machine")
public class MachineController extends BaseController {

    @Autowired
    private MachineService machineService;

    @Autowired
    private AreaService areaService;

    @RequestMapping("/new")
    private String newMachine(Model model) {
        Machine machine = new Machine();
        List<Area> areasList = areaService.getAll();

        model.addAttribute(Constants.MACHINE_UI, machine);
        model.addAttribute(Constants.AREA_LIST_UI, areasList);

        return "/machine/new";
    }

    @RequestMapping("/create")
    public String create(HttpServletRequest request, @Valid Machine machine, BindingResult bindingResult, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        log.debug("Create Machine...");
        List<Area> areasList = areaService.getAll();
        if (bindingResult.hasErrors()) {
            log.error("Error detected in user form...");
            model.addAttribute(Constants.MACHINE_UI, machine);
            model.addAttribute(Constants.AREA_LIST_UI, areasList);
            return "/machine/new";
        }
        
        Machine m=machineService.save(machine);
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_UI, "machine.created.message");
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_ATTRS_UI, new String[]{machine.getName()});
        
        return "redirect:/machine/list";
    }
    
    @RequestMapping("/list")
    public String list(Model model){
        log.debug("get List Area");
        
        //GET MACHINE LIST BY....
        List<Machine> list=machineService.getAll();
        
        model.addAttribute(Constants.MACHINE_LIST_UI, list);
        
        return "/machine/list";
    }
}
