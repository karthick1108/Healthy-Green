/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Karthick Rajasekaran
 */
@Entity
@NamedQueries({
    @NamedQuery(name = UserEntity.GET_ALL_QUERY_NAME, query = "SELECT u FROM UserEntity u WHERE u.userId = :userId"),
@NamedQuery(name = UserEntity.SEARCH_BY_USER_CREDENTIALS, query = "SELECT u FROM UserEntity u WHERE u.username = :username AND u.password = :password ")})
public class UserEntity implements Serializable {

    

    public static final String GET_ALL_QUERY_NAME = "UserEntity.getAll";
    public static final String SEARCH_BY_USER_CREDENTIALS = "UserEntity.findByUserCredentials"; 
    
    private int userId;
    private String username;
    private String password;
    private String userType;
    private Date userDob;
    
    private Set<String> tags;

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    @ElementCollection
    @CollectionTable(name = "tag")
    @Column(name = "value")
    public Set<String> getTags() {
        return tags;
    }
    
    
    public UserEntity() {
        this.tags = new HashSet<>();
    }
    
    public UserEntity(int userid,String username,String password,String userType,Date userDob)
    {
        this.userId=userid;
        this.username=username;
        this.password=password;
        this.userType = userType;
        this.userDob = userDob; 
    }
    

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getUserDob() {
        return userDob;
    }

    public void setUserDob(Date userDob) {
        this.userDob = userDob;
    }

    @Override
    public String toString() {
        return "UserEntity{" + "userId=" + userId + ", username=" + username + ", password=" + password + ", userType=" + userType + ", userDob=" + userDob + ", tags=" + tags + '}';
    }
    
    
    
    
    
}
