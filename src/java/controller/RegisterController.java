
package controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import pojo.User;


@ManagedBean
@RequestScoped
public class RegisterController implements Serializable{

    private Controller controller;
    private Integer id;
    private String name;
    private String password;
    //type = false -> profesor 
    //type = true -> student
    private boolean type;
 
    public RegisterController() {
        
        controller = new Controller();
    }
    
    public String addUserProfessor() {
        User user = new User(this.name, this.password, false);
        this.id = controller.addElement(user);
        this.name = null;
        this.password = null;
        return "index";
    }
    
    public String addUserStudent() {
        User user = new User(this.name, this.password, true);
        this.id = controller.addElement(user);
        this.name = null;
        this.password = null;
        return "index";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
    
    
}
