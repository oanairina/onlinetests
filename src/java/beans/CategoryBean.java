
package beans;

import controller.Controller;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import pojo.Category;
import pojo.Domain;

@ManagedBean
@RequestScoped
public class CategoryBean {
    Controller controller = new Controller();
    
    private Category category = new Category();  
      
    private List<Category> categoryList = new ArrayList<Category>();  

    public void addCategory(){
        controller.addElement(category);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getCategoryList() {
        categoryList = getCategoriesFromDB();
        return categoryList;  
    }  
  
    public void setDomainList(List<Category> categoryList) {  
        this.categoryList = categoryList;  
    }
    
    private List<Category> getCategoriesFromDB(){
        return controller.getCategories();
    }
    
    public void removeCategory(Category category){
        controller.removeCategory(category.getName());
    }
}
