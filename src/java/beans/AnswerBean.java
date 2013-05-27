package beans;

import controller.Controller;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import pojo.Answer;

@ManagedBean
@RequestScoped
public class AnswerBean {
    private Controller controller;
    private List<Answer> answerList;
    private Answer selectedAnswer;

    public AnswerBean() {
        controller = new Controller();
        answerList = new ArrayList<Answer>();
    }

    public Answer getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(Answer answer) {
        this.selectedAnswer = answer;
    }

    public List<Answer> getAnswersList() {
        this.answerList = controller.getAnswers();
        return this.answerList;
    }
}
