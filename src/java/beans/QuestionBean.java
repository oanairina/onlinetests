package beans;

import controller.Controller;
import controller.UserController;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import pojo.Question;

@ManagedBean
@ViewScoped
public class QuestionBean {
    Controller controller = new Controller();
    private Question question = new Question();  
    private List<Question> questionList = new ArrayList<Question>();

    public void addQuestion(){
        question.setUser(((UserController) UserController.getSessionObject("userController")).getLoggedUser());
        controller.addElement(question);
    }
    
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Question> getQuestionList() {
        questionList = getQuestionsFromDB();
        return questionList;  
    }  
  
    public void setQuestionList(List<Question> questionList) {  
        this.questionList = questionList;  
    }
    
    private List<Question> getQuestionsFromDB(){
        return controller.getQuestions();
    }
    
    public void removeQuestion(Question question){
        controller.removeQuestion(question.getId());
    }
}
