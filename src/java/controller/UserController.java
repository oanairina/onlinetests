package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
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
    private User loggedUser;

    public UserController() {
        controller = new Controller();
    }

    public void checkUser() {
        this.loggedUser = controller.getUser(this.name, this.password);
        this.name = null;
        this.password = null;
        if (loggedUser != null) {
            try {
                if (loggedUser.isType()) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("mainStudent.xhtml");
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("mainProfesor.xhtml");
                }
            } catch (IOException ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR !", null));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed: ", "The combination of username and password does not exist"));
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

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public static Object getSessionObject(String objName) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extCtx = ctx.getExternalContext();
        Map<String, Object> sessionMap = extCtx.getSessionMap();
        return sessionMap.get(objName);
    }
}
