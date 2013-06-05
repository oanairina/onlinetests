
package beans;

import controller.Controller;
import controller.DomainController;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import pojo.Domain;

@ManagedBean
@RequestScoped
public class DomainBean {
    Controller controller = new Controller();
    
    private Domain domain = new Domain();  
      
    private List<Domain> domainList = new ArrayList<Domain>();  

    public void addDomain(){
        controller.addElement(domain);
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public List<Domain> getDomainList() {
        domainList = getDomainsFromDB();
        return domainList;  
    }  
  
    public void setDomainList(List<Domain> domainList) {  
        this.domainList = domainList;  
    }
    
    private List<Domain> getDomainsFromDB(){
        return controller.getDomains();
    }
    
    public void removeDomain(Domain domain){
        controller.removeDomain(domain.getName());
    }
}
