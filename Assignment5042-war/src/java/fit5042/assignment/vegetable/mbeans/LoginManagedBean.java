/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment.vegetable.mbeans;

import fit5042.assignment.entities.UserEntity;
import fit5042.assignment.navigation.Navigation;
import fit5042.assignment.userInterface.LoginInterface;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Karthick Rajasekaran
 */
@ManagedBean
@SessionScoped
public class LoginManagedBean implements Serializable{
    
    @EJB
    private LoginInterface login;
    private UserEntity user;
    String variable;
    public LoginManagedBean()
    {
    this.user = new UserEntity();
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UserEntity getUser() {
        return user;
    }
     
    public String chkUser()
    {
        if(this.login.chkValidity(this.user.getUsername(),this.user.getPassword()) == true){
            this.setVariable("Success");
            return Navigation.home.toString();
        }
        else
        {
            this.setVariable("Invalid credentials. Please try again");
            return Navigation.index.toString();
        }
    }

    public String chkLogout()
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();
        return "/index?faces-redirect=true";
    }
    
    public void setLogin(LoginInterface login) {
        this.login = login;
    }

    public LoginInterface getLogin() {
        return login;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }    
    
}
