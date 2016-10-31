/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.service;

import java.util.Date;
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
    public List<WorkOrder> getByArea(Long areaId);
    public WorkOrder create(WorkOrder workOrder);
    public WorkOrder close(WorkOrder workOrder);
    public WorkOrder save(WorkOrder workOrder);
    public WorkOrder update(WorkOrder workOrder);
    public String delete(Long id);
    public WorkOrder setEstimatedReleaseDate(Date releaseDate,Long workOrderId);
    public WorkOrder sendToArea(Long newAreaId,Long workOrderId, Integer scrap);
    public WorkOrder setToMachine(Long machineId,Long workOrderId,Long batchId);
    public WorkOrder pullOutMachine(Long machineId,Long workOrderId,Long batchId,Integer badPieces);
}
