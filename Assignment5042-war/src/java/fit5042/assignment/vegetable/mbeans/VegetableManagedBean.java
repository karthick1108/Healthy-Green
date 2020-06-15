/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment.vegetable.mbeans;

import fit5042.assignment.entities.Country;
import fit5042.assignment.entities.Vegetable;
import fit5042.assignment.entities.Vendor;
import fit5042.assignment.userInterface.VegetableInterface;
import fit5042.assignment.vegetable.webservices.VegetableConfig;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name = "vegetableManagedBean", eager = true)
@SessionScoped

public class VegetableManagedBean implements Serializable {

    @EJB
    private VegetableInterface vegInterface;
    private Vegetable vegInfo;
    private int vegId;

    public int getVegId() {
        return vegId;
    }

    public void setVegId(int vegId) {
        this.vegId = vegId;
    }

    public List<Vegetable> getAllVegetables() {
        try {
            
            List<Vegetable> properties = vegInterface.getAllVegetables();
            return properties;
        } catch (Exception ex) {
            Logger.getLogger(VegetableManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void addVegetable(Vegetable vegetable) {
        try {
            vegInterface.addVegetable(vegetable);
        } catch (Exception ex) {
            Logger.getLogger(VegetableManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Vegetable searchVegetableById(int id) {
        try {
            return vegInterface.searchVegetableById(id);
        } catch (Exception ex) {
            Logger.getLogger(VegetableManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    public List<Vegetable> combineSearchByFK(int vendorId,int countryId) {
        try {
            System.out.println("Inside Managed Bean ");
            return vegInterface.combineSearchByFK(vendorId,countryId);
        } catch (Exception ex) {
            Logger.getLogger(VegetableManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public void addVegetable(fit5042.assignment.vegetable.controller.Vegetable localVegetable) {
        Vegetable vegetable = convertPropertyToEntity(localVegetable);
        try {
            vegInterface.addVegetable(vegetable);
        } catch (Exception ex) {
            Logger.getLogger(VegetableManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Vendor> getAllVendor() throws Exception {
        try {
            return vegInterface.getAllVendor();
        } catch (Exception ex) {
            Logger.getLogger(VegetableManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Country> getAllCountry() throws Exception {
        try {
            return vegInterface.getAllCountry();
        } catch (Exception ex) {
            Logger.getLogger(VegetableManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Vegetable convertPropertyToEntity(fit5042.assignment.vegetable.controller.Vegetable localVegetable) {
        try {
            Vegetable vegetable = new Vegetable();
            vegetable.setVegetableName(localVegetable.getVegetableName());
            vegetable.setVegetableDescription(localVegetable.getVegetableDescription());
            vegetable.setVendor(localVegetable.getVendor());
            vegetable.setCountry(localVegetable.getCountry());
            vegetable.setTags(localVegetable.getTags());
            return vegetable;
        } catch (Exception ex) {
            Logger.getLogger(VegetableManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void removeVegetable(int vegId) {
        try {
            vegInterface.removeVegetable(vegId);
        } catch (Exception ex) {
            Logger.getLogger(VegetableManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editVegetable(Vegetable vegetable) {
        try {
            vegetable.setVegetableName(vegetable.getVegetableName());
            vegetable.setVegetableDescription(vegetable.getVegetableDescription());
            String vendorInfo = vegetable.getVendor().getVendor();
            String countryInfo = vegetable.getCountry().getCountry();
            Vendor newVendor = new Vendor();
            Country newCountry = new Country();
            newVendor.setVendor(vendorInfo);
            newCountry.setCountry(countryInfo);
            vegInterface.editVegetable(vegetable);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vegetable has been updated succesfully"));
        } catch (Exception ex) {
            Logger.getLogger(VegetableManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Vegetable> searchVegetableByName(String vegetableName) {
        try {
            return vegInterface.searchVegetableByName(vegetableName);
        } catch (Exception ex) {
            Logger.getLogger(VegetableManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
     public void removeVegetableByCascade(int countryId) {
        try {
            for(Country country : vegInterface.getAllCountry()){
                if(country.getCountryId()== countryId){
                    for(Vegetable vegetable : vegInterface.searchVegetableByCountry(country)){
                        vegInterface.removeVegetable(vegetable.getVegId());
                    }
                }
            }
            for(Country country : vegInterface.getAllCountry()){
                if(country.getCountryId() == countryId){
                    vegInterface.removeVegetableByCascade(countryId);
                }
            }
            //artifactRepository.removeArtifactForeign(artifactCountryId);
        } catch (Exception ex) {
            Logger.getLogger(VegetableManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
