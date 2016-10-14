/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.service;

import java.util.List;
import mx.edu.um.dii.labinterfaces.diasetproject.model.TimeStored;
import mx.edu.um.dii.labinterfaces.diasetproject.model.TimeStoredArea;
import mx.edu.um.dii.labinterfaces.diasetproject.model.TimeStoredMachine;
import mx.edu.um.dii.labinterfaces.diasetproject.model.WorkOrder;

/**
 *
 * @author laboratoriointerface
 */
public interface TimeStoredService {

    public TimeStored createTimeStored(WorkOrder workOrder);
    public TimeStored closeTimeStored(WorkOrder workOrder);
    public TimeStoredArea createTimeStoredArea(Long workOrderId,Long areaId);
    public TimeStoredArea closeTimeStoredArea(Long workOrderId,Long areaId);
    public TimeStoredMachine createTimeStoredMachine(Long workOrderId,Long machineId);
    public TimeStoredMachine closeTimeStoredMachine(Long workOrderId,Long machineId);
}
