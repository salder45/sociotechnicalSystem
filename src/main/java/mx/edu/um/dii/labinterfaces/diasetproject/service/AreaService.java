/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.service;

import java.util.List;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Area;

/**
 *
 * @author laboratoriointerface
 */
public interface AreaService {
    public List<Area> getAll();
    public Area get(Long id);
    public Area get(String code);
    public Area save(Area area);
    public Area update(Area area);
    public String delete(Long id);
}
