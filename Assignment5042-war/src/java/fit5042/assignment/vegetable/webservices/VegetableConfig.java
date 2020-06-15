/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment.vegetable.webservices;

import fit5042.assignment.userInterface.VegetableInterface;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Karthick Rajasekaran
 */
@Path("restAPI")
public class VegetableConfig {

    @EJB
    VegetableInterface vegInterface;

    @GET
    @Path("welcome/{vegId}")
    @Produces("text/html")

    public String getData(@PathParam("vegId") Integer vegId) {
        try {
            return "<html><body><h1>Hello " + vegInterface.searchVegetableById(vegId).toString() + "!</h1></body></html>";
        } catch (Exception ex) {
            Logger.getLogger(VegetableConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "<html><body><h1>Vegetable not found " + "!</h1></body></html>";
    }
}
