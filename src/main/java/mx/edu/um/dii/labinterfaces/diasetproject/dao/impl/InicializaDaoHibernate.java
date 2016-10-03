/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.dao.impl;

import java.util.HashSet;
import mx.edu.um.dii.labinterfaces.diasetproject.config.Constants;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.BaseDao;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.CredentialDao;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.InicializaDao;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.RoleDao;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.UserDao;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Credential;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Role;
import mx.edu.um.dii.labinterfaces.diasetproject.model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author laboratoriointerface
 */
@Repository
@Transactional
public class InicializaDaoHibernate extends BaseDao implements InicializaDao {

    @Autowired
    private CredentialDao credentialDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void inicializa(String username, String password) {
        log.debug("Init data......");
        log.debug("Create Role");
        //Create role ROLE_ADMIN, this is the role for Factory's administrators
        Role adminRole = roleDao.get(Constants.ROLE_ADMIN);
        if (adminRole == null || adminRole.getAuthority() == null || adminRole.getAuthority().equals("")) {
            adminRole = new Role(Constants.ROLE_ADMIN, "Administrador", "Este es el rol que se le debe asignar a los administradores de la planta, permite accesar a la mayoria de las funcionalidades disponibles.");
            roleDao.save(adminRole);
        }
        //Create role ROLE_SALES, this is the role for salesman
        Role salesRole = roleDao.get(Constants.ROLE_SALES);
        if (salesRole == null || salesRole.getAuthority() == null || salesRole.getAuthority().equals("")) {
            salesRole = new Role(Constants.ROLE_SALES, "Vendedor", "Este es el rol que se le debe asignar al personal del departamento de ventas, permite la creacion de las ordenes de trabajo y demas tareas basicas.");
            roleDao.save(salesRole);
        }
        //Create role ROLE_SALES_ADMIN, this is the role for salesman admin
        Role salesAdminRole = roleDao.get(Constants.ROLE_SALES_ADMIN);
        if (salesAdminRole == null || salesAdminRole.getAuthority() == null || salesAdminRole.getAuthority().equals("")) {
            salesAdminRole = new Role(Constants.ROLE_SALES_ADMIN, "Ventas admin", "Este es el rol que se le debe asignar al encargado del departamento de ventas, tiene los mismos privilegios de un Vendedor ademas da acceso a los reportes.");
            roleDao.save(salesAdminRole);
        }
        //Create role ROLE_PLANNING, this is the role for people from Planning Deparment
        Role planningRole = roleDao.get(Constants.ROLE_PLANNING);
        if (planningRole == null || planningRole.getAuthority() == null || planningRole.getAuthority().equals("")) {
            planningRole = new Role(Constants.ROLE_PLANNING, "Planeacion", "Este es el rol que se le debe asignar al personal del departamento de planeacion, da acceso a las tareas basicas del Depto.");
            roleDao.save(planningRole);
        }
        //Create role ROLE_PLANNING_ADMIN, this is the role for Planning Admin
        Role planningAdminRole = roleDao.get(Constants.ROLE_PLANNING_ADMIN);
        if (planningAdminRole == null || planningAdminRole.getAuthority() == null || planningAdminRole.getAuthority().equals("")) {
            planningAdminRole = new Role(Constants.ROLE_PLANNING_ADMIN, "Planeacion admin", "Este es el rol que se le debe asignar al encargado del departamento de planeacion, da acceso a las tareas basicas del Depto y sus reportes.");
            roleDao.save(planningAdminRole);
        }
        //Create role ROLE_PRODUCTION, this is the role for production personal
        Role productionRole = roleDao.get(Constants.ROLE_PRODUCTION);
        if (productionRole == null || productionRole.getAuthority() == null || productionRole.getAuthority().equals("")) {
            productionRole = new Role(Constants.ROLE_PRODUCTION, "Produccion", "Este es el rol que se le debe asignar al personal del Depto de produccion, da acceso a las tareas basicas del Depto.");
            roleDao.save(productionRole);
        }
        //Create role ROLE_PRODUCTION_ADMIN, this is the role for production admnistrator
        Role productionAdminRole = roleDao.get(Constants.ROLE_PRODUCTION_ADMIN);
        if (productionAdminRole == null || productionAdminRole.getAuthority() == null || productionAdminRole.getAuthority().equals("")) {
            productionAdminRole = new Role(Constants.ROLE_PRODUCTION_ADMIN, "Produccion admin", "Este es el rol que se le debe asignar al encargado del Depto de produccion, da acceso a las tareas basicas del Depto y sus reportes.");
            roleDao.save(productionAdminRole);
        }
        //Create role ROLE_PRODUCTION_ADMIN, this is the role for production admnistrator
        Role qualityRole = roleDao.get(Constants.ROLE_QUALITY);
        if (qualityRole == null || qualityRole.getAuthority() == null || qualityRole.getAuthority().equals("")) {
            qualityRole = new Role(Constants.ROLE_QUALITY, "Calidad", "Este es el rol que se le debe asignar al personal del Depto de calidad, da acceso a las tareas basicas del Depto.");
            roleDao.save(qualityRole);
        }
        //Create role ROLE_PRODUCTION_ADMIN, this is the role for production admnistrator
        Role qualityAdminRole = roleDao.get(Constants.ROLE_QUALITY_ADMIN);
        if (qualityAdminRole == null || qualityAdminRole.getAuthority() == null || qualityAdminRole.getAuthority().equals("")) {
            qualityAdminRole = new Role(Constants.ROLE_QUALITY_ADMIN, "Calidad Admin", "Este es el rol que se le debe asignar al encargado del Depto de calidad, da acceso a las tareas basicas del Depto y sus reportes.");
            roleDao.save(qualityAdminRole);
        }

        //USER
        Role roleUser = roleDao.get(Constants.ROLE_USER);
        log.debug("Role={}", roleUser);
        if (roleUser == null || roleUser.getAuthority() == null || roleUser.getAuthority().equals("")) {
            roleUser = new Role(Constants.ROLE_USER,"Usuario","Rol base de Usuario");
            roleDao.save(roleUser);
        }
        //CREATE USERS
        
        //Create admin/user and custom user
        User userAdmin = userDao.get(Constants.DEFAULT_USER_ADMIN);
        if (userAdmin == null || userAdmin.getId() == null || userAdmin.getId().equals(0L)) {
            String pwd = "admin";
            userAdmin = new User(Constants.DEFAULT_USER_ADMIN, passwordEncoder.encode(pwd), "admin", "admin", "admin");
            userAdmin.addRole(roleDao.get(Constants.ROLE_ADMIN));
            //TODO             
            userDao.save(userAdmin);
            String data = "{\"username\":\"" + userAdmin.getUsername() + "\",\"password\":\"" + pwd + "\"}";
            Credential credential = new Credential(userAdmin.getId(), data);
            credential.setUser(userAdmin);
            credentialDao.save(credential);
        }
        //SALES
        User userVentas = userDao.get(Constants.DEFAULT_USER_SALES);
        if (userVentas == null || userVentas.getId() == null || userVentas.getId().equals(0L)) {
            String pwd = "ventas";
            userVentas = new User(Constants.DEFAULT_USER_SALES, passwordEncoder.encode(pwd), "admin", "admin", "admin");
            userVentas.addRole(roleDao.get(Constants.ROLE_SALES));
            //TODO             
            userDao.save(userVentas);
            String data = "{\"username\":\"" + userVentas.getUsername() + "\",\"password\":\"" + pwd + "\"}";
            Credential credential = new Credential(userVentas.getId(), data);
            credential.setUser(userVentas);
            credentialDao.save(credential);
        }
        User userVentasAdmin = userDao.get(Constants.DEFAULT_USER_SALES_ADMIN);
        if (userVentasAdmin == null || userVentasAdmin.getId() == null || userVentasAdmin.getId().equals(0L)) {
            String pwd = "ventasAdmin";
            userVentasAdmin = new User(Constants.DEFAULT_USER_SALES_ADMIN, passwordEncoder.encode(pwd), "admin", "admin", "admin");
            userVentasAdmin.addRole(roleDao.get(Constants.ROLE_SALES_ADMIN));
            //TODO             
            userDao.save(userVentasAdmin);
            String data = "{\"username\":\"" + userVentasAdmin.getUsername() + "\",\"password\":\"" + pwd + "\"}";
            Credential credential = new Credential(userVentasAdmin.getId(), data);
            credential.setUser(userVentasAdmin);
            credentialDao.save(credential);
        }
        //PLANNING
        User userPlanning = userDao.get(Constants.DEFAULT_USER_PLANNING);
        if (userPlanning == null || userPlanning.getId() == null || userPlanning.getId().equals(0L)) {
            String pwd = "planeacion";
            userPlanning = new User(Constants.DEFAULT_USER_PLANNING, passwordEncoder.encode(pwd), "admin", "admin", "admin");
            userPlanning.addRole(roleDao.get(Constants.ROLE_PLANNING));
            userPlanning.addRole(roleDao.get(Constants.ROLE_PLANNING_ADMIN));
            //TODO             
            userDao.save(userPlanning);
            String data = "{\"username\":\"" + userPlanning.getUsername() + "\",\"password\":\"" + pwd + "\"}";
            Credential credential = new Credential(userPlanning.getId(), data);
            credential.setUser(userPlanning);
            credentialDao.save(credential);
        }
        
        
        //PRODUCTION
        User userProduction = userDao.get(Constants.DEFAULT_USER_PRODUCTION);
        if (userProduction == null || userProduction.getId() == null || userProduction.getId().equals(0L)) {
            String pwd = "produccion";
            userProduction = new User(Constants.DEFAULT_USER_PRODUCTION, passwordEncoder.encode(pwd), "user", "user", "user");
            userProduction.addRole(roleDao.get(Constants.ROLE_PRODUCTION));
            //TODO check encodepassword option
            userDao.save(userProduction);
            String data = "{\"username\":\"" + userProduction.getUsername() + "\",\"password\":\"" + pwd + "\"}";
            Credential credential = new Credential(userProduction.getId(), data);
            credential.setUser(userProduction);
            credentialDao.save(credential);
        }
        
        User userProductionAdmin = userDao.get(Constants.DEFAULT_USER_PRODUCTION_ADMIN);
        if (userProductionAdmin == null || userProductionAdmin.getId() == null || userProductionAdmin.getId().equals(0L)) {
            String pwd = "produccionAdmin";
            userProductionAdmin = new User(Constants.DEFAULT_USER_PRODUCTION_ADMIN, passwordEncoder.encode(pwd), "user", "user", "user");
            userProductionAdmin.addRole(roleDao.get(Constants.ROLE_PRODUCTION_ADMIN));
            //TODO check encodepassword option
            userDao.save(userProductionAdmin);
            String data = "{\"username\":\"" + userProductionAdmin.getUsername() + "\",\"password\":\"" + pwd + "\"}";
            Credential credential = new Credential(userProductionAdmin.getId(), data);
            credential.setUser(userProductionAdmin);
            credentialDao.save(credential);
        }
        
        User userQuality = userDao.get(Constants.DEFAULT_USER_QUALITY);
        if (userQuality == null || userQuality.getId() == null || userQuality.getId().equals(0L)) {
            String pwd = "user";
            userQuality = new User(Constants.DEFAULT_USER_QUALITY, passwordEncoder.encode(pwd), "user", "user", "user");
            userQuality.addRole(roleDao.get(Constants.ROLE_QUALITY));
            //TODO check encodepassword option
            userDao.save(userQuality);
            String data = "{\"username\":\"" + userQuality.getUsername() + "\",\"password\":\"" + pwd + "\"}";
            Credential credential = new Credential(userQuality.getId(), data);
            credential.setUser(userQuality);
            credentialDao.save(credential);
        }
        
        User userQualityAdmin = userDao.get(Constants.DEFAULT_USER_QUALITY_ADMIN);
        if (userQualityAdmin == null || userQualityAdmin.getId() == null || userQualityAdmin.getId().equals(0L)) {
            String pwd = "calidadAdmin";
            userQualityAdmin = new User(Constants.DEFAULT_USER_QUALITY_ADMIN, passwordEncoder.encode(pwd), "user", "user", "user");
            userQualityAdmin.addRole(roleDao.get(Constants.ROLE_QUALITY_ADMIN));
            //TODO check encodepassword option
            userDao.save(userQualityAdmin);
            String data = "{\"username\":\"" + userQualityAdmin.getUsername() + "\",\"password\":\"" + pwd + "\"}";
            Credential credential = new Credential(userQualityAdmin.getId(), data);
            credential.setUser(userQualityAdmin);
            credentialDao.save(credential);
        }

        User customUser = userDao.get(username);
        if (customUser == null || customUser.getId() == null || customUser.getId().equals(0L)) {
            customUser = new User(username, passwordEncoder.encode(password), "customUser", "customUser", "customUser");
            customUser.addRole(roleDao.get(Constants.ROLE_USER));
            //TODO check encodepassword option
            userDao.save(customUser);
            String data = "{\"username\":\"" + customUser.getUsername() + "\",\"password\":\"" + password + "\"}";
            Credential credential = new Credential(customUser.getId(), data);
            credential.setUser(customUser);
            credentialDao.save(credential);
        }

    }

    @Override
    public void inicializaRoles(String username, String password, String roles) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
