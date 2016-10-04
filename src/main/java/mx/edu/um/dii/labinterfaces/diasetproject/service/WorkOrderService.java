/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.service;

import java.util.List;
import mx.edu.um.dii.labinterfaces.diasetproject.model.WorkOrder;

/**
 *
 * @author eder
 */
public interface WorkOrderService {
    public List<WorkOrder> getAll();
    public WorkOrder getById(Long id);
    public WorkOrder getByCode(String code);
    public WorkOrder create(WorkOrder workOrder);
    public WorkOrder save(WorkOrder workOrder);
    public WorkOrder update(WorkOrder workOrder);
    public String delete(Long id);
}
