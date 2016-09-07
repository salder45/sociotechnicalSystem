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
import mx.edu.um.dii.labinterfaces.diasetproject.service.AreaService;
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
@RequestMapping("/area")
public class AreaController extends BaseController {

    @Autowired
    private AreaService areaService;

    @RequestMapping("/new")
    public String newUser(Model model) {
        Area area = new Area();

        model.addAttribute(Constants.AREA_UI, area);

        return "/area/new";
    }

    @RequestMapping("/create")
    public String create(HttpServletRequest request, @Valid Area area, BindingResult bindingResult, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        log.debug("Create Area...");
        if (bindingResult.hasErrors()) {
            log.error("Error detected in user form...");
            model.addAttribute(Constants.AREA_UI, area);
            return "/area/new";
        }

        Area a = areaService.save(area);
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_UI, "area.created.message");
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_ATTRS_UI, new String[]{area.getName()});
        return "redirect:/area/list";
    }
    @RequestMapping("/list")
    public String list(Model model) {
        log.debug("get Area list");
        
        List<Area> list=areaService.getAll();
        model.addAttribute(Constants.AREA_LIST_UI, list);
        
        return "/area/list";
    }
}
