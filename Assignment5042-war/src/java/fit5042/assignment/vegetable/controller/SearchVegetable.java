/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment.vegetable.controller;

import fit5042.assignment.entities.Vegetable;
import fit5042.assignment.vegetable.mbeans.VegetableManagedBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Karthick Rajasekaran
 */
@RequestScoped
@Named("searchVegetable")
public class SearchVegetable {
    
    private Vegetable vegetable;
    
    VegetableApplication app;
    
    private int searchByInt;
    private String searchByString;
    private int vendorId;
    private int countryId;

    public VegetableApplication getApp() {
        return app;
    }

    public void setApp(VegetableApplication app) {
        this.app = app;
    }
    
    public int getSearchByInt() {
        return searchByInt;
    }

    public void setSearchByInt(int searchByInt) {
        this.searchByInt = searchByInt;
    }

    
    public void setVegetable(Vegetable vegetable){
        this.vegetable = vegetable;
    }
    
    public Vegetable getVegetable(){
        return vegetable;
    }

    public String getSearchByString() {
        return searchByString;
    }

    public void setSearchByString(String searchByString) {
        this.searchByString = searchByString;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    
    
       
    
    public SearchVegetable() {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app = (VegetableApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "vegetableApplication");
        
        app.updateVegetableList();
    }

    /**
     * Normally each page should have a backing bean but you can actually do it
     * any how you want.
     *
     * @param property Id 
     */
    public void searchVegetableById(int vegetableId) 
    {
       try
       {
            //search this property then refresh the list in PropertyApplication bean
            app.searchVegetableById(vegetableId);
       }
       catch (Exception ex)
       {
           
       }
    }
    
    public void searchVegetableByName(String vegetableName) 
    {
       try
       {
            //search this property from db via EJB
            app.searchVegetableByName(vegetableName);
       }
       catch (Exception ex)
       {
           
       }
    }
    
    public void combineSearchByFK(int vendorId,int countryId) 
    {
        System.out.println("Inside Search Vegetable");
       try
       {
            //search this property then refresh the list in PropertyApplication bean
            app.combineSearchByFK(vendorId,countryId);
       }
       catch (Exception ex)
       {
           Logger.getLogger(VegetableManagedBean.class.getName()).log(Level.SEVERE, null, ex);
       }
       //Boolean showForm = true;
    }
    
    public void searchAll() 
    {
       try
       {
           //System.out.println("Inside SearchVegetable");
            //return all properties from db via EJB
             app.searchAll();
       }
       catch (Exception ex)
       {
           
       }
    }    
    
}
