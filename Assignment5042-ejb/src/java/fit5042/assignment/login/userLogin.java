/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment.login;

import fit5042.assignment.entities.UserEntity;
import fit5042.assignment.userInterface.LoginInterface;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Karthick Rajasekaran
 */
@Stateless
public class userLogin implements LoginInterface {

    @PersistenceContext(unitName = "Assignment5042-ejbPU")
    private EntityManager entityManager;

    @Override
    public void addUser(UserEntity user) throws Exception {
        entityManager.persist(user);
    }

    @Override
    public UserEntity searchUserById(int id) throws Exception {
        UserEntity user = entityManager.find(UserEntity.class, id);
        user.getTags().size();
        return user;
    }

    @Override
    public List<UserEntity> getAllUser() throws Exception {
        return entityManager.createNamedQuery(UserEntity.GET_ALL_QUERY_NAME).getResultList();
    }

    @Override
    public void removeUser(int userId) throws Exception {
        UserEntity user = this.searchUserById(userId);

        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public void editUser(UserEntity user) throws Exception {
        entityManager.merge(user);
    }

    /*@Override
    public List<UserEntity> searchUserByBudget(double budget) throws Exception {
        return null;
    }*/
    @Override
    public Boolean chkValidity(String username, String password) {
        List<UserEntity> userInfo = this.findByUserCredentials(username, password);
        //System.out.println("Size of user info ::: " + userInfo.size());
        if (!userInfo.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public List<UserEntity> findByUserCredentials(String username, String password) {
        Query query = entityManager.createNamedQuery(UserEntity.SEARCH_BY_USER_CREDENTIALS);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.getResultList();
    }
    
    
}
