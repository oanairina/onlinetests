package controller;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.LazyInitializationException;
import pojo.Category;
import pojo.Domain;

@ManagedBean
@SessionScoped
public class CategoryController implements Serializable {

    private Controller controller;
    private Integer id;
    private String name;
    private String description;
    private Domain domain;
    private List<Category> categoryList;

    public CategoryController() {
        controller = new Controller();
    }

    public String getCategories() {
        try {
            this.categoryList = controller.getCategories();

        } catch (LazyInitializationException e) {
            System.out.println(e.toString());         
        }
        return "categories";
    }

    public String addCategory() {

        Category category = new Category(this.domain, this.name, this.description);
        this.id = controller.addElement(category);
        this.name = null;
        this.description = null;
        return "index";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
