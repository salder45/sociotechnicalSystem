/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.service;

import java.util.List;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Machine;

/**
 *
 * @author laboratoriointerface
 */
public interface MachineService {
    public List<Machine> getAll();
    public List<Machine> getByArea(Long areaId);
    public Machine getById(Long id);
    public Machine getByCode(String code);
    public Machine save(Machine machine);
    public Machine update(Machine machine);
    public String delete(Long id);
}
