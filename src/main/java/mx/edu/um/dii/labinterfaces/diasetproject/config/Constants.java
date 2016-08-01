/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.config;

/**
 *
 * @author laboratoriointerface
 */
public class Constants {

    public static final Integer MAX_SIZE_PASSWORD=20;
    
    public static final String DEFAULT_ENCODING = "UTF-8";
    public static final String APP_NAME = "Diaset";
    /*
    ROLE
    */
    public static final String ROLE_ADMIN="ROLE_ADMIN";
    public static final String ROLE_USER="ROLE_USER";
    /*
    DEFAULT USERS
    */
    public static final String DEFAULT_USER_ADMIN="admin";
    public static final String DEFAULT_USER="user";
    /**
     * Valores para el constructor de Locale
     */
    public static final String LOCALE_LANGUAGE = "es";
    public static final String LOCALE_COUNTRY = "MX";
    public static final String LOCALE_VARIANT = "Traditional_WIN";
    /*
    * Request names
    */
    public static final String USER_UI="user";
    public static final String USER_LIST_UI="usersList";
    public static final String ROLE_LIST_UI="rolesList";
    public static final String ROLE_UI="role";
    public static final String MESSAGE_UI="message";
    public static final String MESSAGE_ATTRS_UI="messageAttrs";
}
