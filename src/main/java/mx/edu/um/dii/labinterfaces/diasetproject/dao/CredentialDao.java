/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.dao;

import mx.edu.um.dii.labinterfaces.diasetproject.model.Credential;

/**
 *
 * @author laboratoriointerface
 */
public interface CredentialDao {
    public Credential get(Long id);
    public Credential get(String barcode);
    public Credential save(Credential credential);
    public Credential update(Credential credential);
    public String delete(Long id);
}
