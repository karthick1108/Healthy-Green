/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment.vegetable.controller;

import fit5042.assignment.entities.Vegetable;
import fit5042.assignment.vegetable.mbeans.VegetableManagedBean;
import java.util.ArrayList;
import javax.el.ELContext;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "vegetableApplication")
@ApplicationScoped

public class VegetableApplication {
    
    @ManagedProperty(value="#{vegetableManagedBean}")
    VegetableManagedBean vegetableManagedBean;
    
    private ArrayList<Vegetable> vegetableInfo;
    private boolean showFForm = true;
    
    public boolean isShowForm(){
        return showFForm;
    }
    
    // Add some property data from db to app 
    public VegetableApplication() throws Exception {       
        vegetableInfo = new ArrayList<>();
        
        //instantiate propertyManagedBean
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        vegetableManagedBean = (VegetableManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "vegetableManagedBean");
        if (vegetableInfo != null && vegetableInfo.size() > 0)
        {
        } else {
        //get properties from db 
        updateVegetableList();
        }
    }
    
    public ArrayList<Vegetable> getVegetableInfo() {
        return vegetableInfo;
    }
    
    private void setVegetableInfo(ArrayList<Vegetable> newVegetableInfo) {
        this.vegetableInfo = newVegetableInfo;
    }

    public void updateVegetableList()
    {
        if (vegetableInfo != null && vegetableInfo.size() > 0)
        {
            
        }
        else
        {
            vegetableInfo.clear();

            for (fit5042.assignment.entities.Vegetable vegetable : vegetableManagedBean.getAllVegetables())
            {
                vegetableInfo.add(vegetable);
            }

            setVegetableInfo(vegetableInfo);
        }
    }
    
    public void searchVegetableById(int vegetableId)
    {
        vegetableInfo.clear();
        
        vegetableInfo.add(vegetableManagedBean.searchVegetableById(vegetableId));
    }
    
    public void combineSearchByFK(int vendorId, int countryId) {

        System.out.println("Inside Application");
        vegetableInfo.clear();

        for (fit5042.assignment.entities.Vegetable vegetable : vegetableManagedBean.combineSearchByFK(vendorId, countryId)) {
            vegetableInfo.add(vegetable);
        }

        setVegetableInfo(vegetableInfo);

    }
    
    public void searchVegetableByName(String vegetableName)
    {
        vegetableInfo.clear();
        
        for (fit5042.assignment.entities.Vegetable vegetable : vegetableManagedBean.searchVegetableByName(vegetableName))
        {
            vegetableInfo.add(vegetable);
        }
        
        setVegetableInfo(vegetableInfo);
    }
    
    public void searchAll()
    {
        //System.out.println("Inside Vegetable Application");
        vegetableInfo.clear();
        
        for (fit5042.assignment.entities.Vegetable vegetable : vegetableManagedBean.getAllVegetables())
        {
            vegetableInfo.add(vegetable);
        }
        
        setVegetableInfo(vegetableInfo);
    }
   
}
