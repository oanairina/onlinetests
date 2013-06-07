package beans;

import controller.Controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import pojo.Answer;
import pojo.Category;
import pojo.Domain;
import pojo.Question;

@ManagedBean
@ViewScoped
public class AnswerBean implements Serializable {
    private Controller controller;
    private List<Answer> answerList = new ArrayList<Answer>();
    private Answer answer = new Answer();
    private Category category;
    private Domain domain;
    private Question selectedQuestion;
    private List<Domain> domainList; 
    private List<Category> categoryList;
    private List<Question> questionList;
    
    public AnswerBean() {
        controller = new Controller();
        answerList = new ArrayList<Answer>();
        domainList = controller.getDomains();
        categoryList = controller.getCategories();  
        questionList = controller.getQuestions();
    }
    
    public String reinit(){
        this.answer = new Answer();
        return null;
    }
    
    public List<Answer> getAnswersList() {
        this.answerList = controller.getAnswers();
        return this.answerList;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public List<Domain> getDomainList() {
        return domainList;
    }

    public void setDomainList(List<Domain> domainList) {
        this.domainList = domainList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
    
    public void handleDomainChange() {  
        if(domain !=null)  
            categoryList = controller.getCategories(domain);  
        else { 
            categoryList = controller.getCategories(); 
        }  
    }  
    
    public void handleCategoryChange() {  
        if(category !=null)  
            questionList = controller.getQuestions(category);  
        else { 
            questionList = controller.getQuestions(); 
        }  
    }
    
    public void handleQuestionChange(){
        answerList = controller.getAnswersByQuestionId(selectedQuestion.getId());
    }
    
    public void removeAnswer(Answer answer){
        answerList.remove(answer);
    }
    
    public void saveAnswers(){
        for(Answer answerFromList:answerList){
            answerFromList.setQuestion(selectedQuestion);
            controller.addElement(answerFromList);
        }
    }

    public Question getSelectedQuestion() {
        return selectedQuestion;
    }

    public void setSelectedQuestion(Question selectedQuestion) {
        this.selectedQuestion = selectedQuestion;
    }
    
}
