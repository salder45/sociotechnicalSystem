/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Size;
import mx.edu.um.dii.labinterfaces.diasetproject.config.Constants;
import mx.edu.um.dii.labinterfaces.diasetproject.utils.ProjectUtils;
import org.jasypt.util.text.BasicTextEncryptor;

/**
 *
 * @author laboratoriointerface
 */
@Entity
@Table(name = "credentials")
public class Credential implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Version
    private Integer version;
    @Column(name = "barcode")
    @Size(max = 15)
    private String barcodeValue;
    private String credentialData;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Transient
    private BasicTextEncryptor bte;
    //Save id image
    @Lob
    private byte[] picture;
    public Credential(){
        bte=new BasicTextEncryptor();
        bte.setPassword(Constants.PASSWORD_JASYPT);
    }
    
    public Credential(Long userId,String data){
        //this.barcodeValue=calculateBarcode(userId);
        this.barcodeValue=ProjectUtils.generateCode(userId, Constants.USER_START_CODE);
        if(bte==null){
            bte=new BasicTextEncryptor();            
        }
        bte.setPassword(Constants.PASSWORD_JASYPT);
        //
        this.credentialData=bte.encrypt(data);
    }
    
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
     * @return the barcodeValue
     */
    public String getBarcodeValue() {
        return barcodeValue;
    }

    /**
     * @param barcodeValue the barcodeValue to set
     */
    public void setBarcodeValue(String barcodeValue) {
        this.barcodeValue = barcodeValue;
    }

    /**
     * @return the credentialData
     */
    public String getCredentialData() {
        //Decrypt        
        return bte.decrypt(credentialData);
    }

    /**
     * @param credentialData the credentialData to set
     */
    public void setCredentialData(String credentialData) {
        //Encrypt        
        this.credentialData = bte.encrypt(credentialData);
    }
    
     /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the picture
     */
    public byte[] getPicture() {
        return picture;
    }

    /**
     * @param picture the picture to set
     */
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
    
    
    @Override
    public int hashCode(){
        int hash=7;
        hash=11*hash+Objects.hash(this.Id,this.barcodeValue,this.credentialData);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj==null){
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Credential other=(Credential)obj;
        return Objects.equals(this.Id, other.Id)&Objects.equals(this.barcodeValue, other.barcodeValue)&Objects.equals(this.credentialData, other.credentialData);
    }
    
    @Override
    public String toString() {
        return "{Credential{Id="+this.Id+",barcode="+this.barcodeValue+",credentialData="+this.credentialData+"}}";
    }
   
    private String calculateBarcode(Long userId){
        String idStr=userId.toString();
        String rtn=Constants.DEFAULT_STRING_BARCODE.substring(0, Constants.DEFAULT_STRING_BARCODE.length()-idStr.length())+idStr;
        return rtn;
    }
}
