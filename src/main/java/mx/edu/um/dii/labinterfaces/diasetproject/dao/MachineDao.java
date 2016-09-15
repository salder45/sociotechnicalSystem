/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.dao;

import java.util.List;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Machine;

/**
 *
 * @author laboratoriointerface
 */
public interface MachineDao {
    public List<Machine> getMachines(Machine machine);
    public Machine get(Long id);
    public Machine get(String code);
    public Machine save(Machine machine);
    public Machine update(Machine machine);
    public String delete(Long id);
}
