/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.dao;

/**
 *
 * @author laboratoriointerface
 */
public interface InicializaDao {

    public void inicializa(String username, String password);

    public void inicializaRoles(String username, String password, String roles);
}
