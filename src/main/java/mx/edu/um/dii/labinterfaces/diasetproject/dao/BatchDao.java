/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.dao;

import java.util.List;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Batch;

/**
 *
 * @author laboratoriointerface
 */
public interface BatchDao {
    public List<Batch> get(Batch batch);

    public Batch get(Long id);

    public Batch getByDescription(String description);

    public Batch save(Batch batch);

    public Batch update(Batch batch);

    public String delete(Long id);
}
