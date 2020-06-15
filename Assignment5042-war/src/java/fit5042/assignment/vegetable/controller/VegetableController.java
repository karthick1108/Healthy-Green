/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment.vegetable.controller;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;


/**
 *
 * @author Karthick Rajasekaran
 */

@Named(value = "vegetableController")
@RequestScoped

public class VegetableController {
    
    private int vegId; //this propertyId is the index, don't confuse with the Property Id

    public int getVegId() {
        return vegId;
    }

    public void setVegId(int vegId) {
        this.vegId = vegId;
    }
    private fit5042.assignment.entities.Vegetable vegetable;
    

    public VegetableController() {
        // Assign property identifier via GET param 
        //this propertyID is the index, don't confuse with the Property Id
        vegId = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("vegetableID"));
        // Assign property based on the id provided 
        vegetable = getVegetable();
    }

    public fit5042.assignment.entities.Vegetable getVegetable() {
        if (vegetable == null) {
            // Get application context bean PropertyApplication 
            ELContext context
                    = FacesContext.getCurrentInstance().getELContext();
            VegetableApplication app
                    = (VegetableApplication) FacesContext.getCurrentInstance()
                            .getApplication()
                            .getELResolver()
                            .getValue(context, null, "vegetableApplication");
            // -1 to propertyId since we +1 in JSF (to always have positive property id!) 
            return app.getVegetableInfo().get(--vegId); //this propertyId is the index, don't confuse with the Property Id
        }
        return vegetable;
    }
}
