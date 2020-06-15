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
@Table(name = "COUNTRY")
@NamedQueries({@NamedQuery(name = Country.GET_ALL_QUERY_NAME, query = "SELECT c FROM Country c")})
public class Country implements Serializable {

    
    
    public static final String GET_ALL_QUERY_NAME = "Country.getAll";
    
    
    private int countryId;
    private String country;
    
     public Set<Vegetable> vegetables;
     
      public Country() {
    }

    public Country(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
        this.vegetables = new HashSet<>();
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setVegetables(Set<Vegetable> vegetables) {
        this.vegetables = vegetables;
    }

    @OneToMany(mappedBy = "country")
    public Set<Vegetable> getVegetables() {
        return vegetables;
    }
    
    @Id
    @GeneratedValue
    @Column(name = "country_id")
    public int getCountryId() {
        return countryId;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.countryId;
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
        final Country other = (Country) obj;
        if (this.countryId != other.countryId) {
            return false;
        }
        return true;
    }   
      
    @Override
    public String toString() {
        return "Country{" + "countryId=" + countryId + ", country=" + country +'}';
    }  
    
}
