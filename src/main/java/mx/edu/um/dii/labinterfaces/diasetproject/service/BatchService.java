/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.service;

import java.util.List;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Batch;

/**
 *
 * @author laboratoriointerface
 */
public interface BatchService {
    public List<Batch> getAll();
    public List<Batch> getBatchsListByWorkOrderAndStatus(Long workOrderId,String status);
    public Batch getById(Long id);
    public Batch save(Batch batch);
    public Batch update(Batch batch);
    public String delete(Long id);
}
