package converter;

import controller.CategoryController;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import pojo.Category;
import controller.Controller;

@FacesConverter("categoryConverter")
public class CategoryConverter implements Converter {

    private static CategoryController categoryController = new CategoryController();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Controller controller = new Controller();
        List<Category> categoryList = controller.getCategories();
        Category category = null;
        for (Category c : categoryList) {
            if (c.getName().equals(value)) {
                category = c;
                break;
            }
        }
        return category;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }
}
