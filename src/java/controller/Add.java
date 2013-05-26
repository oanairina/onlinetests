/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import pojo.Answer;
import pojo.Category;
import pojo.Domain;
import pojo.Question;
import pojo.User;

/**
 *
 * @author Irene
 */
@ManagedBean
@ViewScoped
public class Add {

    private Controller controller;
    private Integer id;
    private String text;
    private String userName;
    private String userPassword;
    private boolean status;
    private boolean type;
    private List<Category> categoryList;
    private List<Domain> domainList;
    private List<Question> questionList;
    private Category category;
    private Domain domain;
    private Question question;
    private User user;

    public Add() {
        controller = new Controller();
    }

    public String getDomains() {
        this.domainList = controller.getDomains();
        return null;
    }

    public String getCategories() {
        this.categoryList = controller.getCategories(this.domain);
        return null;
    }

    public String getQuestions() {
        this.questionList = controller.getQuestions(this.category);
        return null;
    }
    


    public String addAnswer() {
        Answer answer = new Answer(question, text, status);
        this.id = controller.addElement(answer);
        //this.text = null;
        //this.status = false;
        return "index";
    }
    
    public String addUser(){
        User user = new User(userName, userPassword, type);
     this.id = controller.addElement(user);
     return "index";
    }

    
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Domain> getDomainList() {
        return domainList;
    }

    public void setDomainList(List<Domain> domainList) {
        this.domainList = domainList;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}
