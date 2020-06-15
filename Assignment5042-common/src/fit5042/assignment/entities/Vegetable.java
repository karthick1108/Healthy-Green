/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Karthick Rajasekaran
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Vegetable.GET_ALL_QUERY_NAME, query = "SELECT v FROM Vegetable v order by v.vegId desc"),
@NamedQuery(name = Vegetable.GET_COMBINE_SEARCH, query = "SELECT v FROM Vegetable v where v.vendor.vendorId = :vendorId AND v.country.countryId= :countryId")})
public class Vegetable implements Serializable {

    

    public static final String GET_ALL_QUERY_NAME = "Vegetable.getAll";
    public static final String GET_COMBINE_SEARCH = "Vegetable.combineSearch";
    private int vegId;
    
    //@Column(name="vegetableName",nullable = false)
    
    @Size(min=5,max=20,message ="Vegetable Name must have atleast 5 characters")
    @NotNull(message = "Vegetable name cannot be empty")
    @Pattern(regexp = "^[a-zA-Z ]*$",message = "Vegetable Name invalid.Use only alphabets")
    private String vegetableName;
    //@Column(name="vegetableDescription",nullable = false)
    @Size(min=10,max=1000,message ="Vegetable Description must have atleast 10 characters")
    @NotNull(message = "Vegetable Description cannot be empty")
    private String vegetableDescription;
    @NotNull(message = "Select a Vendor")
    private Vendor vendor;
    @NotNull(message = "Select a Vendor")
    private Country country;
    
    private Set<String> tags;

    public Vegetable() {
        this.tags = new HashSet<>();
    }

    public Vegetable(int vegId, String vegetableName, String vegetableDescription, Vendor vendor,Country country,Set<String> tags) {
        this.vegId = vegId;
        this.vegetableName = vegetableName;
        this.vegetableDescription = vegetableDescription;
        this.country = country;
        this.vendor = vendor;
        this.tags = tags;
    }
    

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "vegId")
    public int getVegId() {
        return vegId;
    }

    public void setVegId(int vegId) {
        this.vegId = vegId;
    }
     
    public String getVegetableName() {
        return vegetableName;
    }

    public void setVegetableName(String vegetableName) {
        this.vegetableName = vegetableName;
    }
     
    public String getVegetableDescription() {
        return vegetableDescription;
    }

    public void setVegetableDescription(String vegetableDescription) {
        this.vegetableDescription = vegetableDescription;
    }
    
    @ManyToOne
    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
    
    @ManyToOne
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
    
    
    
    @ElementCollection
    @CollectionTable(name = "tag")
    @Column(name = "value")
    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
    
    @Override
    public String toString() {
        return "Vegetable{" + "vegId=" + vegId + ", vegetableName=" + vegetableName + ", vegetableDescription=" + vegetableDescription + ", vendor=" + vendor +'}';
    }
    
}
