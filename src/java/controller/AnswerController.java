package controller;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pojo.Answer;
import pojo.Controller;
import pojo.Question;

@ManagedBean
@SessionScoped
public class AnswerController implements Serializable  {

    private Controller controller;
    private Integer id;
    private String text;
    private boolean status;
    private List<Answer> answerList;
    private Question question;
    
    public AnswerController() {
        controller = new Controller();
    }
    
    public String getAnswers(){
        this.answerList = controller.getAnswers();
        return "answers";
    }
    
    public String addAnswer(){
        Answer answer = new Answer(question, text, status);
        this.id = controller.addElement(answer);
        this.text = null;
        this.status = false;
        
        return "index";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
    

}
