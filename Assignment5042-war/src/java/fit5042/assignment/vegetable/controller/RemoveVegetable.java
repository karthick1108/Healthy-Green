/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment.vegetable.controller;

import fit5042.assignment.vegetable.mbeans.VegetableManagedBean;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Karthick Rajasekaran
 */
@RequestScoped
@Named("removeVegetable")
public class RemoveVegetable {
    
    @ManagedProperty(value = "#{vegetableManagedBean}")
    VegetableManagedBean vegetableManagedBean;

    private boolean showForm = true;

    //private final ArrayList<fit5042.tutex.repository.entities.Property> properties;
    private Vegetable vegetable;

    VegetableApplication app;

    public void setVegetable(Vegetable vegetable) {
        this.vegetable = vegetable;
    }

    public Vegetable getVegetable() {
        return vegetable;
    }

    public boolean isShowForm() {
        return showForm;
    }

    public RemoveVegetable() {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app = (VegetableApplication) FacesContext.getCurrentInstance()
                .getApplication()
                .getELResolver()
                .getValue(context, null, "vegetableApplication");

        app.updateVegetableList();

        //instantiate propertyManagedBean
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        vegetableManagedBean = (VegetableManagedBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "vegetableManagedBean");
    }

    /**
     * @param property Id
     */
    public void removeVegetable(int vegId) {
        try {
            System.out.println("ID is: "+""+vegId);
            //remove this property from db via EJB
            vegetableManagedBean.removeVegetable(vegId);

            //refresh the list in PropertyApplication bean
            app.searchAll();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vegetable has been deleted succesfully"));
        } catch (Exception ex) {

        }
        showForm = true;

    }
    
    public void removeVegetableByCascade(int countryId) {
        //this is the local artifact, not the entity
        try {
            //add this artifact to db via EJB
            vegetableManagedBean.removeVegetableByCascade(countryId);

            //refresh the list in PropertyApplication bean
            app.searchAll();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vegetable has been deleted succesfully"));
            //refresh the artifact list in propertyApplication bean
        } catch (Exception ex) {

        }
        showForm = true;
    }
    
}
