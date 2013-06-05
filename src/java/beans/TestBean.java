package beans;

import controller.Controller;
import controller.QuestionController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import pojo.Question;
import pojo.Test;

@ManagedBean
@ViewScoped
public class TestBean implements Serializable {

    Controller controller = new Controller();
    QuestionController questionController = new QuestionController();
    private Question question = new Question();
    private List<Question> questions;
    private List<Question> selectedQuestions;
    private List<Question> filteredQuestions;
    private Test test = new Test();
    private final int MAX_SCORE = 100;
    private int score;

    public TestBean() {
        questions = new ArrayList<Question>();
        selectedQuestions = new ArrayList<Question>();
    }

    public List<Question> getQuestions() {
        questions = questionController.getQuestionList();
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getSelectedQuestions() {
        return selectedQuestions;
    }

    public void setSelectedQuestions(List<Question> selectedQuestions) {
        this.selectedQuestions = selectedQuestions;
    }
    
    public void selectQuestion(Question question){
        if((score + question.getDifficultyLevel()) <= MAX_SCORE){
        for(Question q:selectedQuestions){
            if(question.getId().intValue() == q.getId().intValue()){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null,"The question was already added"));
                return;
            }   
        }
        selectedQuestions.add(question);
        score += question.getDifficultyLevel();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null,"Please select a question with a difficulty lower than "+question.getDifficultyLevel()));
        }
    }
     public void removeQuestion(Question question){
        this.selectedQuestions.remove(question);
        score -= question.getDifficultyLevel();
    }

    public List<Question> getFilteredQuestions() {
        return filteredQuestions;
    }

    public void setFilteredQuestions(List<Question> filteredQuestions) {
        this.filteredQuestions = filteredQuestions;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public void saveTest(){
        int testId = controller.addElement(test);
        for(Question q:selectedQuestions){
            //controller.addElement()
        }
    }
   
     
}
