/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment.vegetable;

import fit5042.assignment.entities.Country;
import fit5042.assignment.entities.Vegetable;
import fit5042.assignment.entities.Vendor;
import fit5042.assignment.userInterface.VegetableInterface;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class VegetableBean implements VegetableInterface {

    @PersistenceContext(unitName = "Assignment5042-ejbPU")
    private EntityManager entityManager;

    @Override
    public List<Vegetable> getAllVegetables() throws Exception {
        return entityManager.createNamedQuery(Vegetable.GET_ALL_QUERY_NAME).getResultList();
    }
    
    @Override
    public Vegetable searchVegetableById(int id) throws Exception {
        Vegetable vegetable = entityManager.find(Vegetable.class, id);
        vegetable.getTags();
        return vegetable;
    }
    
    @Override
    public Country searchCountryById(int countryId) throws Exception {
        Country country = entityManager.find(Country.class, countryId);
        //country.getTags();
        return country;
    }
 
    
    @Override
    public void addVegetable(Vegetable vegetable) throws Exception {
        List<Vegetable> vegetables =  entityManager.createNamedQuery(Vegetable.GET_ALL_QUERY_NAME).getResultList();
        vegetable.setVegId(vegetables.get(0).getVegId() + 1);
        entityManager.persist(vegetable);
    }
    
    @Override
    public List<Vendor> getAllVendor() throws Exception {
        return entityManager.createNamedQuery(Vendor.GET_ALL_QUERY_NAME).getResultList();
    }
    
    @Override
    public List<Country> getAllCountry() throws Exception {
        return entityManager.createNamedQuery(Country.GET_ALL_QUERY_NAME).getResultList();
    }
    
    @Override
    public void editVegetable(Vegetable vegetable) throws Exception {   
         try {
            entityManager.merge(vegetable);
        } catch (Exception ex) {
            
        }
    }
    
    @Override
    public void removeVegetable(int vegId) throws Exception {
        Vegetable vegetable = this.searchVegetableById(vegId);
        System.out.println("Vegetable Info"+vegetable);

        if (vegetable != null) {
            entityManager.remove(vegetable);
        }
    }
    
    @Override
    public List<Vegetable> searchVegetableByName(String name) throws Exception {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(Vegetable.class);
        Root<Vegetable> p = query.from(Vegetable.class);
        query.select(p).where(builder.equal(p.get("vegetableName"), name));
        List<Vegetable> lp = entityManager.createQuery(query).getResultList();       
        return lp;
    }
    
    @Override
    public List<Vegetable> combineSearchByFK(int vendorId, int countryId) throws Exception{
        Query query = entityManager.createNamedQuery(Vegetable.GET_COMBINE_SEARCH);
        query.setParameter("vendorId", vendorId);
        query.setParameter("countryId", countryId);
        return query.getResultList();
    }
    
    @Override
    public void removeVegetableByCascade(int countryId) throws Exception {
        Country country = this.searchCountryById(countryId);

        if (country != null) {
            entityManager.remove(country);
        }
    }
    
    @Override
    public Set<Vegetable> searchVegetableByCountry(Country country)
    {
        country = entityManager.find(Country.class, country.getCountryId());
        country.getVegetables().size();
        entityManager.refresh(country);
        return country.getVegetables();
    }
    
    
}
