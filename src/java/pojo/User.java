package pojo;
// Generated May 26, 2013 2:43:18 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
public class User  implements java.io.Serializable {


     private Integer id;
     private String name;
     private String password;
     private boolean type;
     private Set questions = new HashSet(0);

    public User() {
    }
	
    public User(String name, String password, boolean type) {
        this.name = name;
        this.password = password;
        this.type = type;
    }
    public User(String name, String password, boolean type, Set questions) {
       this.name = name;
       this.password = password;
       this.type = type;
       this.questions = questions;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isType() {
        return this.type;
    }
    
    public void setType(boolean type) {
        this.type = type;
    }
    public Set getQuestions() {
        return this.questions;
    }
    
    public void setQuestions(Set questions) {
        this.questions = questions;
    }




}


