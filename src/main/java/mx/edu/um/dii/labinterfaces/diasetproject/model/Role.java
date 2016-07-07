/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author laboratoriointerface
 */
@Entity
@Table(name="roles")
public class Role implements Serializable,GrantedAuthority{
    @Id
    private String authority;
    
    public Role(){
    }
    
    public Role(String authority){
        this.authority=authority;
    }
    
    /**
     * @return the authority
     */
    @Override
    public String getAuthority(){
        return this.authority;
    }
    
    /**
     * @param authority the authority to set
     */
    public void setAuthority(String authority){
        this.authority=authority;
    }
    
    @Override
    public int hashCode(){
        int hash=5;
        hash=97*Objects.hashCode(this.authority);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj==null){
            return false;
        }
        
        if(getClass()!=obj.getClass()){
            return false;
        }
        
        final Role other=(Role)obj;
        return Objects.equals(this.authority, other.authority);        
    }
    
    @Override
    public String toString(){
        return "Role{authority="+authority+"}";
    }
}
