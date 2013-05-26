package controller;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pojo.Category;
import pojo.Controller;
import pojo.Question;
import pojo.User;

@ManagedBean
@SessionScoped
public class QuestionController implements Serializable {

    private Controller controller;
    private Integer id;
    private String text;
    private Integer dificulty_level;
    private List<Question> questionList;
    private Integer[] dificulty_levels;
    private Category category;
    private User user;

    public QuestionController() {
        controller = new Controller();
        dificulty_levels = new Integer[]{1, 2, 3, 4, 5};
    }

    public String getQuestions() {
        this.questionList = controller.getQuestions();
        return "questions";
    }

    public String addQuestion() {
        Question question = new Question(this.user, this.category, this.text, this.dificulty_level);
        this.id = controller.addElement(question);
        this.user = null;
        this.text = null;
        this.dificulty_level = 0;

        return "index";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getDificulty_level() {
        return dificulty_level;
    }

    public void setDificulty_level(Integer dificulty_level) {
        this.dificulty_level = dificulty_level;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer[] getDificulty_levels() {
        return dificulty_levels;
    }

    public void setDificulty_levels(Integer[] dificulty_levels) {
        this.dificulty_levels = dificulty_levels;
    }
}
