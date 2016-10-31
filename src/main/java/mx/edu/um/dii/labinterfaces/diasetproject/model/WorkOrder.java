/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author laboratoriointerface
 */
@Entity
@Table(name = "work_orders")
public class WorkOrder implements Serializable{
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
    private String code;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "estimated_release_date", nullable = false)
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm")
    private Date estimatedReleaseDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "release_date", nullable = false)
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm")
    private Date releaseDate;
    private Integer piecesNumber=0;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id")
    private Seller seller;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "area_id")
    private Area areaActual;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "machine_id")
    private Machine machineActual;
    /*
    batchs
    */
    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "workOrder",fetch = FetchType.EAGER)
    private List<Batch> batchs;
    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "workOrder",fetch = FetchType.EAGER)
    private List<TimeStored> timesStored;
    //Transient fields
    @Transient
    private Integer badPieces=0;
    @Transient
    private Batch batch;
    

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
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the estimatedReleaseDate
     */
    public Date getEstimatedReleaseDate() {
        return estimatedReleaseDate;
    }

    /**
     * @param estimatedReleaseDate the estimatedReleaseDate to set
     */
    public void setEstimatedReleaseDate(Date estimatedReleaseDate) {
        this.estimatedReleaseDate = estimatedReleaseDate;
    }

    /**
     * @return the releaseDate
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * @param releaseDate the releaseDate to set
     */
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * @return the piecesNumber
     */
    public Integer getPiecesNumber() {
        return piecesNumber;
    }

    /**
     * @param piecesNumber the piecesNumber to set
     */
    public void setPiecesNumber(Integer piecesNumber) {
        this.piecesNumber = piecesNumber;
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the seller
     */
    public Seller getSeller() {
        return seller;
    }

    /**
     * @param seller the seller to set
     */
    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    /**
     * @return the batchs
     */
    public List<Batch> getBatchs() {
        return batchs;
    }

    /**
     * @param batchs the batchs to set
     */
    public void setBatchs(List<Batch> batchs) {
        this.batchs = batchs;
    }

    /**
     * @return the timesStored
     */
    public List<TimeStored> getTimesStored() {
        return timesStored;
    }

    /**
     * @param timesStored the timesStored to set
     */
    public void setTimesStored(List<TimeStored> timesStored) {
        this.timesStored = timesStored;
    }

    /**
     * @return the areaActual
     */
    public Area getAreaActual() {
        return areaActual;
    }

    /**
     * @param areaActual the areaActual to set
     */
    public void setAreaActual(Area areaActual) {
        this.areaActual = areaActual;
    }

    /**
     * @return the machineActual
     */
    public Machine getMachineActual() {
        return machineActual;
    }

    /**
     * @param machineActual the machineActual to set
     */
    public void setMachineActual(Machine machineActual) {
        this.machineActual = machineActual;
    }
    
    @Override
    public int hashCode() {
     int hash = 7;
        hash = 11 * hash + Objects.hash(this.Id, this.code);        
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
        
        final WorkOrder other=(WorkOrder)obj;
        return Objects.equals(this.Id, other.Id)&&Objects.equals(this.code, other.code);
    }
    
    @Override
    public String toString() {
        return "{WorkOrder{Id:"+this.Id+", code:"+this.code+"}}";
    }    

    /**
     * @return the badPieces
     */
    public Integer getBadPieces() {
        return badPieces;
    }

    /**
     * @param badPieces the badPieces to set
     */
    public void setBadPieces(Integer badPieces) {
        this.badPieces = badPieces;
    }

    /**
     * @return the batch
     */
    public Batch getBatch() {
        return batch;
    }

    /**
     * @param batch the batch to set
     */
    public void setBatch(Batch batch) {
        this.batch = batch;
    }
}
