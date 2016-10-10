/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.dao;

import java.util.List;
import mx.edu.um.dii.labinterfaces.diasetproject.model.TimeStored;

/**
 *
 * @author laboratoriointerface
 */
public interface TimeStoredDao {
    public List<TimeStored> getTimesStored(TimeStored timeStored);
    public TimeStored getLastTimeStoredByWorkOrderBy(Long workOrderId);
    public TimeStored getTimeStored(Long id);
    public TimeStored save(TimeStored timeStored);
    public TimeStored update(TimeStored timeStored);
    public String delete(Long id);    
}
