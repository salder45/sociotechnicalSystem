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
import javax.persistence.Version;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author laboratoriointerface
 */
@Entity
@Table(name = "batchs")
public class Batch implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Version
    private Integer version;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created", nullable = false)
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm zzz")
    private Date dateCreated;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated", nullable = false)
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm zzz")
    private Date lastUpdated;
    @Column(nullable = false)
    private String status;
    private String description;
    private Boolean existDraw;
    private Boolean sharpening;
    private Boolean coveringRequired;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "work_order_id")
    private WorkOrder workOrder;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "draw_id")
    private Draw draw;
    @OneToMany(mappedBy = "batch", fetch = FetchType.EAGER)
    private List<Piece> pieces;

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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the existDraw
     */
    public Boolean getExistDraw() {
        return existDraw;
    }

    /**
     * @param existDraw the existDraw to set
     */
    public void setExistDraw(Boolean existDraw) {
        this.existDraw = existDraw;
    }

    /**
     * @return the sharpening
     */
    public Boolean getSharpening() {
        return sharpening;
    }

    /**
     * @param sharpening the sharpening to set
     */
    public void setSharpening(Boolean sharpening) {
        this.sharpening = sharpening;
    }

    /**
     * @return the coveringRequired
     */
    public Boolean getCoveringRequired() {
        return coveringRequired;
    }

    /**
     * @param coveringRequired the coveringRequired to set
     */
    public void setCoveringRequired(Boolean coveringRequired) {
        this.coveringRequired = coveringRequired;
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

    /**
     * @return the draw
     */
    public Draw getDraw() {
        return draw;
    }

    /**
     * @param draw the draw to set
     */
    public void setDraw(Draw draw) {
        this.draw = draw;
    }

    /**
     * @return the pieces
     */
    public List<Piece> getPieces() {
        return pieces;
    }

    /**
     * @param pieces the pieces to set
     */
    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hash(this.Id, this.description);
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

        final Batch other = (Batch) obj;
        return Objects.equals(this.Id, other.Id) && Objects.equals(this.description, other.description);

    }

    @Override
    public String toString() {
        return "{Batch{Id:" + this.Id + ", status:" + this.status + " , description: " + this.description + "}}";
    }
}
