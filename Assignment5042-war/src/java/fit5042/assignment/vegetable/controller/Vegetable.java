/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment.vegetable.controller;

import fit5042.assignment.entities.Country;
import fit5042.assignment.entities.Vendor;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Karthick Rajasekaran
 */
@RequestScoped
@Named(value = "vegetable")
public class Vegetable implements Serializable{
   
    private int vegId;
    private HashSet<String> tags;  
    @NotNull(message = "Vegetable name cannot be empty")
    @Size(min=5,max=20,message ="Vegetable Name must have atleast 5 characters")
    @Pattern(regexp = "^[a-zA-Z ]*$",message = "Vegetable Name invalid.Use only alphabets")
    private String vegetableName;
    @Size(min=10,max=1000,message ="Vegetable Description must have atleast 10 characters")
    @NotNull(message = "Vegetable Description cannot be empty")
    private String vegetableDescription; 
    @NotNull(message = "Select a Vendor")
    private Vendor vendor;   
    @NotNull(message = "Select a Country")
    private Country country;
    private Set<fit5042.assignment.entities.Vegetable> vegetables;

        public Vegetable() {
        this.tags = new HashSet<>();
    }

    public Vegetable(int vegId, String vegetableName, String vegetableDescription, Vendor vendor, Country country, Set<String> tags) {
        this.vegId = vegId;
        this.vegetableName = vegetableName;
        this.vegetableDescription = vegetableDescription;
        this.vendor = vendor;
        this.country = country;
        this.tags = (HashSet<String>) tags;
    }

   
     public int getVegId() {
        return vegId;
    }
    
     public void setVegId(int vegId) {
        this.vegId = vegId;
    }
     
    public String getVegetableName() {
        return vegetableName;
    }
    
    public Vendor getVendor() {
        return vendor;
    }
    
     public Country getCountry() {
        return country;
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
    
    public Set<fit5042.assignment.entities.Vegetable> getVegetables() {
        return vegetables;
    }

    public void setVegetables(Set<fit5042.assignment.entities.Vegetable> vegetables) {
        this.vegetables = vegetables;
    }
    
    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
    
    public void setCountry(Country country) {
        this.country = country;
    }
  
    public HashSet<String> getTags() {
        return tags;
    }

    public void setTags(HashSet<String> tags) {
        this.tags = tags;
    }
    
    @Override
    public String toString() {
        return "Vegetable{" + "vegId=" + vegId + ", vegetableName=" + vegetableName + ", vegetableDescription=" + vegetableDescription + ", vendor=" + vendor + ", country=" + country + ",tags=" + tags + '}';
    }
    
    
}
