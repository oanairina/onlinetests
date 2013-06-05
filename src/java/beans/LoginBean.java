
package beans;

import controller.Controller;
import controller.UserController;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import pojo.User;

      
@ManagedBean
@RequestScoped
public class LoginBean {
    
    UserController userController = new UserController();
    Controller controller = new Controller();
    
    private String name;
    private String password;
    User user = new User();

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
    
    public void checkUser(){
         userController.checkUser();
         
    }
  
}  
   

