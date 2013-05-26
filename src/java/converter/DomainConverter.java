/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import controller.DomainController;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpSession;
import controller.Controller;
import pojo.Domain;

/**
 *
 * @author Irene
 */
@FacesConverter("domainConverter")
public class DomainConverter implements Converter{
    
    private static DomainController domainController = new DomainController();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Controller controller = new Controller();
        List<Domain> domainList = controller.getDomains();
        Domain domain = null;
        for (Domain d : domainList) {
            if (d.getName().equals(value)) {
                domain = d;
                break;
            }
        }
        return domain;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }
    
}
