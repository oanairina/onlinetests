package controller;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pojo.Domain;

@ManagedBean
@SessionScoped
public class DomainController implements Serializable {

    private Controller controller;
    private String name;
    private Integer id;
    private List<Domain> domainList;

    public DomainController() {
        controller = new Controller();
    }

    public String getDomains() {
        this.domainList = controller.getDomains();
        return "domains";
    }

    public void addDomain() {
        Domain domain = new Domain(this.name);
        this.id = controller.addElement(domain);
        this.name = null;
    }
    /*
     public void removeDomain(String name) {
     Domain domain = controller.getDomainByName(name);
     if(domain != null){
     controller.removeElement(domain);
     }
     }
     */

//    public Domain find(String name) {
//        Domain domain = null;
//        for (Domain d : domainList) {
//            if (d.getName().equals(name)) {
//                domain = d;
//                break;
//            }
//        }
//        return domain;
//    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Domain> getDomainList() {
        this.domainList = controller.getDomains();
        return domainList;
    }

    public void setDomainList(List<Domain> domainList) {
        this.domainList = domainList;
    }
}
