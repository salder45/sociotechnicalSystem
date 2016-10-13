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
@DiscriminatorValue("Area")
public class TimeStoredArea extends TimeStored{
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "area_id")
    private Area area;

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
        return Constants.TYPE_TIMESTORED_AREA;
    }
}
