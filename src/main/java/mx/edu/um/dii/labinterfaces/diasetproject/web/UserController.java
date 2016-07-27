/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import mx.edu.um.dii.labinterfaces.diasetproject.config.Constants;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.UserDao;
import mx.edu.um.dii.labinterfaces.diasetproject.model.User;
import mx.edu.um.dii.labinterfaces.diasetproject.service.UserService;
import mx.edu.um.dii.labinterfaces.diasetproject.utils.Environment;
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
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private Environment enviroment;

    @RequestMapping("/profile")
    public String getPerfil(Model model) {
        User user = userService.get(enviroment.getUser().getId());
        model.addAttribute(Constants.USER_UI, user);
        return "user/edit";
    }

    @Transactional
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(HttpServletRequest request, @Valid User user, BindingResult bindingResult, Errors errors, Model modelo, RedirectAttributes redirectAttributes) {
        log.debug("Update user...");
        if (bindingResult.hasErrors()) {
            log.error("Error detected in user form...");
            return "/user/edit";
        }
        
        User u=userService.update(user);
        
        //
        redirectAttributes.addFlashAttribute("message",
                "user.updated.message");
        redirectAttributes.addFlashAttribute("messageAttrs",
                new String[]{user.getUsername()});
        //
        log.debug("User is {}", user);
        return "redirect:/user/show/"+user.getId();
    }
    
    @RequestMapping("/show/{id}")
    public String show(@PathVariable Long id,Model model){
        log.debug("Showing user {}",id);
        User user=userService.get(id);
        model.addAttribute("usuario", user);
        return "/user/show";
    }
    
    @RequestMapping("list")
    public String list(Model model){
        return "/user/list";
    }

}
