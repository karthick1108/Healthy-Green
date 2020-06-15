/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment.vegetable.controller;

//import fit5042.assignment.entities.Vegetable;
import fit5042.assignment.vegetable.mbeans.VegetableManagedBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author Karthick Rajasekaran
 */
@RequestScoped
@Named("addVegetable")
public class AddVegetable {

    @ManagedProperty(value = "#{vegetableManagedBean}")
    VegetableManagedBean vegetableManagedBean;

    private boolean showForm = true;

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

    public AddVegetable() {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app = (VegetableApplication) FacesContext.getCurrentInstance()
                .getApplication()
                .getELResolver()
                .getValue(context, null, "vegetableApplication");

        //instantiate propertyManagedBean
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        vegetableManagedBean = (VegetableManagedBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "vegetableManagedBean");
    }

    public void addVegetable(Vegetable localVegetable) {
        //this is the local property, not the entity
        try {
            //add this property to db via EJB
            vegetableManagedBean.addVegetable(localVegetable);
            app.searchAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vegetable has been added succesfully"));

        } catch (EJBException ex) {
            Exception exception = ex.getCausedByException();
            if (exception instanceof ConstraintViolationException) {
                ConstraintViolationException constraint = (ConstraintViolationException) exception;
                StringBuilder message = new StringBuilder();
                message.append("\n");
                if (constraint.getConstraintViolations() != null) {
                    for (ConstraintViolation violation : constraint.getConstraintViolations()) {
                        message.append(violation.getMessage());
                        message.append("\n");
                    }
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vegetable not added. Please check again"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vegetable not added. Please check again"));
            }

        } catch (Exception ex) {
            Logger.getLogger(VegetableManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        showForm = true;
    }
}
