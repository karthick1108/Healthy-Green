/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment.userInterface;
import fit5042.assignment.entities.UserEntity;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Karthick Rajasekaran
 */
@Remote
public interface LoginInterface {
    
    /**
     * Add the property being passed as parameter into the repository
     *
     * @param property - the property to add
     */
    public void addUser(UserEntity user) throws Exception;

    /**
     * Search for a property by its property ID
     *
     * @param id - the propertyId of the property to search for
     * @return the property found
     */
    public UserEntity searchUserById(int id) throws Exception;

    /**
     * Return all the properties in the repository
     *
     * @return all the properties in the repository
     */
    public List<UserEntity> getAllUser() throws Exception;
    
    
    /**
     * Remove the property, whose property ID matches the one being passed as parameter, from the repository
     *
     * @param propertyId - the ID of the property to remove
     */
    public void removeUser(int userId) throws Exception;
    
    /**
     * Update a property in the repository
     *
     * @param property - the updated information regarding a property
     */
    public void editUser(UserEntity user) throws Exception;
    
    /**
     * Search for properties whose price is less than or equal to a budget
     *
     * @param budget - the budget that the price of the returned properties must be lower than or equal to
     * @return the properties found
     */
    //public List<UserEntity> searchUserByBudget(double Id) throws Exception;
    public List<UserEntity> findByUserCredentials(String username,String password) throws Exception;
    
    public Boolean chkValidity(String username,String password);
    
}
