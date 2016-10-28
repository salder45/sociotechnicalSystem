/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import mx.edu.um.dii.labinterfaces.diasetproject.config.Constants;

/**
 *
 * @author laboratoriointerface
 */
@Entity
@DiscriminatorValue("Machine")
public class TimeStoredMachine extends TimeStored{
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "machine_id")
    private Machine machine;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "area_id")
    private Area area;

    /**
     * @return the machine
     */
    public Machine getMachine() {
        return machine;
    }

    /**
     * @param machine the machine to set
     */
    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    /**
     * @return the area
     */
    public Area getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(Area area) {
        this.area = area;
    }
    
    @Override
    public String getType() {
        return Constants.TYPE_TIMESTORED_MACHINE;
    }
}
