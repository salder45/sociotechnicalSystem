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
@Table(name = "results")
public class Result implements Serializable {

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
    @Column(name = "result_value")
    private String value;
    private Boolean pass;
    /*
    
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "machine_id")
    private Machine machine;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_result_id")
    private TypeResult typeResult;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "piece_id")
    private Piece piece;
    @OneToMany(mappedBy = "result", fetch = FetchType.EAGER)
    private List<NoteResult> notes;

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
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the pass
     */
    public Boolean getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(Boolean pass) {
        this.pass = pass;
    }

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
     * @return the typeResult
     */
    public TypeResult getTypeResult() {
        return typeResult;
    }

    /**
     * @param typeResult the typeResult to set
     */
    public void setTypeResult(TypeResult typeResult) {
        this.typeResult = typeResult;
    }

    /**
     * @return the piece
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * @param piece the piece to set
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * @return the notes
     */
    public List<NoteResult> getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(List<NoteResult> notes) {
        this.notes = notes;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hash(this.Id, this.value);
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

        final Result other = (Result) obj;
        return Objects.equals(this.Id, other.Id) && Objects.equals(this.value, other.value);
    }

    @Override
    public String toString() {
        return "{Result{Id:" + this.Id + ", value:" + this.value + ", status:" + this.status + "}}";
    }
}
