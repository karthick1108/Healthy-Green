/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Karthick Rajasekaran
 */
@Entity
@Table(name = "VENDOR")
@NamedQueries({@NamedQuery(name = Vendor.GET_ALL_QUERY_NAME, query = "SELECT v FROM Vendor v")})
public class Vendor implements Serializable {  

    
    public static final String GET_ALL_QUERY_NAME = "Vendor.getAll";
    
    private int vendorId;
    private String vendor;
    //private String country;
    
    public Set<Vegetable> vegetables;
    
    public Vendor() {
    }
    
    public Vendor(int vendorId, String vendor) {
        this.vendorId = vendorId;
        this.vendor = vendor;
        //this.country = country;
        this.vegetables = new HashSet<>();
    }
    
    @Id
    @GeneratedValue
    @Column(name = "vendor_id")
    public int getVendorId() {
        return vendorId;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
    

    @OneToMany(mappedBy = "vendor")
    public Set<Vegetable> getVegetables() {
        return vegetables;
    }
    

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    

    

    public void setVegetables(Set<Vegetable> vegetables) {
        this.vegetables = vegetables;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.vendorId;
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
        final Vendor other = (Vendor) obj;
        if (this.vendorId != other.vendorId) {
            return false;
        }
        return true;
    }   
    
    @Override
    public String toString() {
        return "Vendor{" + "vendorId=" + vendorId + ", vendor=" + vendor + '}';
    }
    
}
