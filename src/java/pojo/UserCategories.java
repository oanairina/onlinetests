package pojo;


public class UserCategories  implements java.io.Serializable {


     private Integer id;
     private User user;
     private Category category;
     private int level;

    public UserCategories() {
    }

    public UserCategories(User user, Category category, int level) {
       this.user = user;
       this.category = category;
       this.level = level;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public Category getCategory() {
        return this.category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
    public int getLevel() {
        return this.level;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }




}


