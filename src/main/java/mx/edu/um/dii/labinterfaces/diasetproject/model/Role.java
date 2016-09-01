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
import org.springframework.security.core.authority.AuthorityUtils;

/**
 *
 * @author laboratoriointerface
 */
@Entity
@Table(name="roles")
public class Role implements Serializable,GrantedAuthority{
    @Id
    private String authority;
    private String name;
    private String description;
    public Role(){
    }
    
    public Role(String authority){
        this.authority=authority;
    }
    
    public Role(String authority,String name, String description){
        this.authority=authority;
        this.name=name;
        this.description=description;
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

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
