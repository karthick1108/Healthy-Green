/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment.userInterface;

import fit5042.assignment.entities.Country;
import fit5042.assignment.entities.Vegetable;
import fit5042.assignment.entities.Vendor;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Karthick Rajasekaran
 */
public interface VegetableInterface {
    
    
     public List<Vegetable> getAllVegetables() throws Exception;
     public Vegetable searchVegetableById(int id) throws Exception;
     public Country searchCountryById(int countryId) throws Exception;
     public void addVegetable(Vegetable vegetable) throws Exception;
     public void editVegetable(Vegetable vegetable) throws Exception;
     public void removeVegetable(int vegId) throws Exception;
     public List<Vendor> getAllVendor() throws Exception;
     public List<Country> getAllCountry() throws Exception;
     public List<Vegetable> searchVegetableByName(String vegetableName) throws Exception;
     public List<Vegetable> combineSearchByFK(int vendorId,int countryId) throws Exception;
     public void removeVegetableByCascade(int countryId) throws Exception;
     public Set<Vegetable> searchVegetableByCountry(Country country);       
}
