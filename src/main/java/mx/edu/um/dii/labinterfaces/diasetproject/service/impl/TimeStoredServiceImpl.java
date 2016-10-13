/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.service.impl;

import java.util.Date;
import java.util.List;
import mx.edu.um.dii.labinterfaces.diasetproject.config.Constants;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.AreaDao;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.TimeStoredDao;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Area;
import mx.edu.um.dii.labinterfaces.diasetproject.model.TimeStored;
import mx.edu.um.dii.labinterfaces.diasetproject.model.TimeStoredArea;
import mx.edu.um.dii.labinterfaces.diasetproject.model.TimeStoredMachine;
import mx.edu.um.dii.labinterfaces.diasetproject.model.WorkOrder;
import mx.edu.um.dii.labinterfaces.diasetproject.service.AreaService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.BaseService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.MachineService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.TimeStoredService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.WorkOrderService;
import mx.edu.um.dii.labinterfaces.diasetproject.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author laboratoriointerface
 */
@Service
public class TimeStoredServiceImpl extends BaseService implements TimeStoredService {

    @Autowired
    private TimeStoredDao timeStoredDao;

    @Autowired
    private WorkOrderService workOrderService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private MachineService machineService;

    @Override
    public TimeStored createTimeStored(WorkOrder workOrder) {
        log.debug("createTimeStored");
        TimeStored timeStored = new TimeStored();
        workOrder = workOrderService.getById(workOrder.getId());
        timeStored.setWorkOrder(workOrder);
        timeStored.setStartTime(new Date());
        timeStored.setFinishTime(ProjectUtils.getDefaultDate());
        timeStored.setStatus(Constants.STATUS_ACTIVE);

        timeStoredDao.save(timeStored);

        return timeStored;
    }

    @Override
    public TimeStored closeTimeStored(WorkOrder workOrder) {
        log.debug("closeTimeStored");
        TimeStored timeStored = new TimeStored();
        workOrder = workOrderService.getById(workOrder.getId());
        for (TimeStored ts : workOrder.getTimesStored()) {
            if (Constants.TYPE_TIMESTORED.equals(ts.getType()) && ts.getStatus().equals(Constants.STATUS_ACTIVE)) {
                timeStored = ts;
            }
        }
        timeStored = timeStoredDao.getTimeStored(timeStored.getId());
        timeStored.setFinishTime(new Date());
        timeStored.setStatus(Constants.STATUS_CLOSED);

        timeStoredDao.update(timeStored);

        return timeStored;
    }

    @Override
    public TimeStoredArea createTimeStoredArea(WorkOrder workOrder) {
        log.debug("createTimeStoredArea");
        workOrder = workOrderService.getById(workOrder.getId());
        Area area = areaService.get(workOrder.getAreaActual().getId());
        TimeStoredArea timeStoredArea = new TimeStoredArea();
        timeStoredArea.setWorkOrder(workOrder);
        timeStoredArea.setStartTime(new Date());
        timeStoredArea.setFinishTime(ProjectUtils.getDefaultDate());
        timeStoredArea.setStatus(Constants.STATUS_ACTIVE);
        timeStoredArea.setArea(area);

        timeStoredDao.save(timeStoredArea);

        return timeStoredArea;
    }

    @Override
    public TimeStoredArea closeTimeStoredArea(WorkOrder workOrder) {
        log.debug("closeTimeStoredArea");
        TimeStoredArea timeStoredArea = new TimeStoredArea();
        workOrder = workOrderService.getById(workOrder.getId());
        for (TimeStored ts : workOrder.getTimesStored()) {
            log.debug(ts.getId() + " - " + ts.getType());
            if (Constants.TYPE_TIMESTORED_AREA.equals(ts.getType())) {
                TimeStoredArea tmp = (TimeStoredArea) ts;
                log.debug(tmp.toString());
                log.debug(tmp.getArea().toString());
                if (tmp.getStatus().equals(Constants.STATUS_ACTIVE) && tmp.getArea().getId().equals(workOrder.getAreaActual().getId())) {
                    timeStoredArea = tmp;
                }
            }
        }

        timeStoredArea = (TimeStoredArea) timeStoredDao.getTimeStored(timeStoredArea.getId());
        timeStoredArea.setFinishTime(new Date());
        timeStoredArea.setStatus(Constants.STATUS_CLOSED);

        timeStoredDao.update(timeStoredArea);

        return timeStoredArea;
    }

    @Override
    public TimeStoredMachine createTimeStoredMachine(WorkOrder workOrder) {
        log.debug("createTimeStoredMachine");
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TimeStoredMachine closeTimeStoredMachine(WorkOrder workOrder) {
        log.debug("closeTimeStoredMachine");
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
