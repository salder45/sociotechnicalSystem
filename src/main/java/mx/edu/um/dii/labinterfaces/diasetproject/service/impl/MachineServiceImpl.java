/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.service.impl;

import java.util.List;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.AreaDao;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.MachineDao;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Area;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Machine;
import mx.edu.um.dii.labinterfaces.diasetproject.service.BaseService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author laboratoriointerface
 */
@Service
public class MachineServiceImpl extends BaseService implements MachineService{

    @Autowired
    private MachineDao machineDao;
    
    @Autowired
    private AreaDao areaDao;
    
    @Override
    public List<Machine> getAll() {
        return machineDao.getMachines(new Machine());
    }

    @Override
    public Machine getById(Long id) {
        return machineDao.get(id);
    }

    @Override
    public Machine getByCode(String code) {
        return machineDao.get(code);
    }

    @Override
    public Machine save(Machine machine) {
        if(machine.getArea()!=null&&machine.getArea().getId()!=null&&machine.getArea().getId()!=0L){
            Area area=areaDao.get(machine.getArea().getId());
            machine.setArea(area);
        }
        return machineDao.save(machine);
    }

    @Override
    public Machine update(Machine machine) {
        if(machine.getArea()!=null&&machine.getArea().getId()!=null&&machine.getArea().getId()!=0L){
            Area area=areaDao.get(machine.getArea().getId());
            machine.setArea(area);
        }
        return machineDao.update(machine);
    }

    @Override
    public String delete(Long id) {
        return machineDao.delete(id);
    }

    @Override
    public List<Machine> getByArea(Long areaId) {
        Machine machine=new Machine();
        Area area=new Area();
        area.setId(areaId);
        machine.setArea(area);
        
        return machineDao.getMachines(machine);
    }
    
}
