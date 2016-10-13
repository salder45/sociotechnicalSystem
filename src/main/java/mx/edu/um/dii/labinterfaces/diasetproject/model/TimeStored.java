/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import mx.edu.um.dii.labinterfaces.diasetproject.config.Constants;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author laboratoriointerface
 */
@Entity
@Table(name = "stored_times")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "STORED_BY",
        discriminatorType = DiscriminatorType.STRING
)
public class TimeStored implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Version
    private Integer version;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created", nullable = false)
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm")
    private Date dateCreated;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated", nullable = false)
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm")
    private Date lastUpdated;
    @Column(nullable = false)
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_time", nullable = false)
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm")
    private Date startTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "finish_time", nullable = false)
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm")
    private Date finishTime;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "work_order_id")
    private WorkOrder workOrder;

    /**
     * @return the Id
     */
    public Long getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(Long Id) {
        this.Id = Id;
    }

    /**
     * @return the version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * @return the dateCreated
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the lastUpdated
     */
    public Date getLastUpdated() {
        return lastUpdated;
    }

    /**
     * @param lastUpdated the lastUpdated to set
     */
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the finishTime
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * @param finishTime the finishTime to set
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * @return the workOrder
     */
    public WorkOrder getWorkOrder() {
        return workOrder;
    }

    /**
     * @param workOrder the workOrder to set
     */
    public void setWorkOrder(WorkOrder workOrder) {
        this.workOrder = workOrder;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hash(this.Id, this.status);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final TimeStored other = (TimeStored) obj;
        return Objects.equals(this.Id, other.Id) && Objects.equals(this.status, other.status);
    }

    @Override
    public String toString() {
        return "{TimeStored{Id:" + this.Id + ", status:" + this.status + "}}";
    }

    public String getType() {
        return Constants.TYPE_TIMESTORED;
    }
}
