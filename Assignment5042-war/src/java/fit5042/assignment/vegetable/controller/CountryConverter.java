/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment.vegetable.controller;

import fit5042.assignment.vegetable.mbeans.VegetableManagedBean;
import java.util.List;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Karthick Rajasekaran
 */
@FacesConverter(forClass = fit5042.assignment.entities.Country.class, value = "countryConverter")
public class CountryConverter implements Converter {
    
    @ManagedProperty(value = "#{vegetableManagedBean}")
    VegetableManagedBean vegetableManagedBean;

    public List<fit5042.assignment.entities.Country> countryDB; //= propertyManagedBean.getAllContactPeople();

    public CountryConverter() {
        try {
            //instantiate propertyManagedBean
            ELContext elContext = FacesContext.getCurrentInstance().getELContext();
            vegetableManagedBean = (VegetableManagedBean) FacesContext.getCurrentInstance().getApplication()
                    .getELResolver().getValue(elContext, null, "vegetableManagedBean");

            countryDB = vegetableManagedBean.getAllCountry();
        } catch (Exception ex) {

        }
    }

    //this method is for converting the submitted value (as String) to the contact person object
    //the reason for using this method is, the dropdown box in the xhtml does not capture the contact person object, but the String.
    public fit5042.assignment.entities.Country getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(submittedValue);

                for (fit5042.assignment.entities.Country c : countryDB) {
                    if (c.getCountryId() == number) {
                        return c;
                    }
                }

            } catch (NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid Country"));
            }
        }

        return null;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((fit5042.assignment.entities.Country) value).getCountryId());
        }
    }
}
