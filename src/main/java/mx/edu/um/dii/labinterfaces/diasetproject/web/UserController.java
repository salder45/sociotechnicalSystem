/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.web;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import mx.edu.um.dii.labinterfaces.diasetproject.config.Constants;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Credential;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Role;
import mx.edu.um.dii.labinterfaces.diasetproject.model.User;
import mx.edu.um.dii.labinterfaces.diasetproject.service.RoleService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.UserService;
import mx.edu.um.dii.labinterfaces.diasetproject.utils.Environment;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
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
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private Environment enviroment;

    @RequestMapping("/new")
    public String newUser(Model model) {
        log.debug("Load User...");
        User user = new User();
        user.setCredential(new Credential());
        model.addAttribute(Constants.USER_UI, user);
        List<Role> allRoleList = roleService.getAll();
        model.addAttribute(Constants.ROLE_LIST_UI, allRoleList);
        return "/user/new";
    }

    @RequestMapping("/create")
    public String create(HttpServletRequest request, @Valid User user, BindingResult bindingResult, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        log.debug("Create User...");
        if (bindingResult.hasErrors()) {
            log.error("Error detected in user form...");
            List<Role> allRoleList = roleService.getAll();
            model.addAttribute(Constants.ROLE_LIST_UI, allRoleList);
            return "/user/edit";
        }
        User u = userService.save(user);

        redirectAttributes.addFlashAttribute(Constants.MESSAGE_UI,
                "user.created.message");
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_ATTRS_UI,
                new String[]{user.getUsername()});

        return "redirect:/user/list";
    }

    @RequestMapping("/profile")
    public String getPerfil(Model model) {
        log.debug("Profile User...");
        User user = userService.get(enviroment.getUser().getId());
        model.addAttribute(Constants.USER_UI, user);
        List<Role> allRoleList = roleService.getAll();
        model.addAttribute(Constants.ROLE_LIST_UI, allRoleList);
        return "user/edit";
    }

    @Transactional
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(HttpServletRequest request, @Valid User user, BindingResult bindingResult, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        log.debug("Update user...");
        if (bindingResult.hasErrors()) {
            log.error("Error detected in user form...");
            return "/user/edit";
        }

        User u = userService.update(user);

        //
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_UI,
                "user.updated.message");
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_ATTRS_UI,
                new String[]{user.getUsername()});
        //
        log.debug("User is {}", user);
        return "redirect:/user/show/" + user.getId();
    }

    @RequestMapping("/show/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.debug("Showing user {}", id);
        User user = userService.get(id);
        model.addAttribute("user", user);
        return "/user/show";
    }

    @RequestMapping("list")
    public String list(Model model) {
        log.debug("get User List");
        List<User> userList = userService.getAll();
        model.addAttribute(Constants.USER_LIST_UI, userList);
        return "/user/list";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        log.debug("edit User...");
        User user = userService.get(id);
        model.addAttribute(Constants.USER_UI, user);
        List<Role> allRoleList = roleService.getAll();
        model.addAttribute(Constants.ROLE_LIST_UI, allRoleList);
        return "user/edit";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        log.debug("delete User...");
        try {
            String username = userService.delete(id);
            redirectAttributes.addFlashAttribute(Constants.MESSAGE_UI,
                    "user.deleted.message");
            redirectAttributes.addFlashAttribute(Constants.MESSAGE_ATTRS_UI,
                    new String[]{username});
            log.debug("User: {} deleted", username);
        } catch (Exception e) {
        }
        return "redirect:/user/list";
    }

    @RequestMapping("/credential/{id}")
    public void pdfCredential(@PathVariable Long id, HttpServletResponse response) throws JRException, IOException {
        log.debug("pdfCredential...");

        Map<String, Object> params = new HashMap<>();
        //get user
        User user = userService.get(id);
        params.put("fullName", user.getFullName());
        params.put("barcode", user.getCredential().getBarcodeValue());
        //Load logo
        BufferedImage bf = ImageIO.read(getClass().getResource(Constants.LOGO_PATH));
        params.put("logo", bf);
        //Load profile pic
        //paramName=profilePic
        BufferedImage bfProfile = ImageIO.read(getClass().getResource(Constants.DEFAULT_PHOTO_PATH));
        params.put("profilePic", bfProfile);
        //
        JasperDesign jd = JRXmlLoader.load(this.getClass().getResourceAsStream("/reports/Credential.jrxml"));
        JasperReport jr = JasperCompileManager.compileReport(jd);
        JasperPrint jp = JasperFillManager.fillReport(jr, params, new JREmptyDataSource());
        byte[] bytes = JasperExportManager.exportReportToPdf(jp);

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + user.getCredential().getBarcodeValue() + ".pdf\""));
        response.setContentLength(bytes.length);
        try (BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream())) {
            bos.write(bytes);
            bos.flush();
        }

        //return bytes;
    }

}
