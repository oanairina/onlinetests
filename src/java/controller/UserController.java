package controller;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pojo.User;

@ManagedBean
@SessionScoped
public class UserController implements Serializable {

    private Controller controller;
    private Integer id;
    private String name;
    private String password;
    //type = false -> profesor 
    //type = true -> student
    private boolean type;
    private List<User> userList;

    public UserController() {
        controller = new Controller();
    }

//    public String addUserProfessor() {
//        User user = new User(this.name, this.password, false);
//        this.id = controller.addElement(user);
//        this.name = null;
//        this.password = null;
//        return "index";
//    }
//    
//    public String addUserStudent() {
//        User user = new User(this.name, this.password, true);
//        this.id = controller.addElement(user);
//        this.name = null;
//        this.password = null;
//        return "index";
//    }
   
    public String checkUser(){
        Integer result = controller.getUser(this.name, this.password);
        if(result!=null){
            return "success";
        } else {
            return "error";
        }
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

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}
