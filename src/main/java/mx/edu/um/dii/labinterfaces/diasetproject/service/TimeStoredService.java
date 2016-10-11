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
    public TimeStoredArea createTimeStoredArea(WorkOrder workOrder);
    public TimeStoredArea closeTimeStoredArea(WorkOrder workOrder);
    public TimeStoredMachine createTimeStoredMachine(WorkOrder workOrder);
    public TimeStoredMachine closeTimeStoredMachine(WorkOrder workOrder);
}
