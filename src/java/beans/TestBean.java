package beans;

import controller.Controller;
import controller.QuestionController;
import controller.UserController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import pojo.Answer;
import pojo.Category;
import pojo.Question;
import pojo.Test;
import pojo.User;
import pojo.UserCategories;

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
    private final int MAX_SCORE = 50;
    private final int STARTING_LEVEL = 3;
    private int scoreProgressBar;
    private Map<Integer, List<Question>> questionsMap = new HashMap<Integer, List<Question>>();
    private int currentLevel;
    private Random random = new Random();
    private Category selectedCategory;
    private Question currentQuestion;
    private List<Answer> answerList;
    private List<Answer> selectedAnswers;
    private boolean startState = true;
    private boolean finishState = false;
    private boolean duringTestState = false;
    private int currentScore;
    private int wrongCounter;
    private int correctCounter;

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

    public void selectQuestion(Question question) {
        if ((scoreProgressBar + question.getDifficultyLevel()) <= MAX_SCORE) {
            for (Question q : selectedQuestions) {
                if (question.getId().intValue() == q.getId().intValue()) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "The question was already added"));
                    return;
                }
            }
            selectedQuestions.add(question);
            scoreProgressBar += question.getDifficultyLevel();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Please select a question with a difficulty lower than " + question.getDifficultyLevel()));
        }
    }

    public void removeQuestion(Question question) {
        this.selectedQuestions.remove(question);
        scoreProgressBar -= question.getDifficultyLevel();
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

    public int getScoreProgressBar() {
        return scoreProgressBar;
    }

    public void setScoreProgressBar(int scoreProgressBar) {
        this.scoreProgressBar = scoreProgressBar;
    }
    
    public Category getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Category selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public List<Answer> getAnswerList() {
        answerList = controller.getAnswersByQuestionId(currentQuestion.getId());
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public List<Answer> getSelectedAnswers() {
        return selectedAnswers;
    }

    public void setSelectedAnswers(List<Answer> selectedAnswers) {
        this.selectedAnswers = selectedAnswers;
    }

    public boolean isStartState() {
        return startState;
    }

    public void setStartState(boolean startState) {
        this.startState = startState;
    }

    public boolean isFinishState() {
        return finishState;
    }

    public void setFinishState(boolean finishState) {
        this.finishState = finishState;
    }

    public boolean isDuringTestState() {
        return duringTestState;
    }

    public void setDuringTestState(boolean duringTestState) {
        this.duringTestState = duringTestState;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }
    

    public void saveTest() {
        int testId = controller.addElement(test);
        for (Question q : selectedQuestions) {
            //controller.addElement()
        }
    }

    private void getQuestionsByCategory(Category category) {
        List<Question> questionList = controller.getQuestions(category);
        List<Question> listLevel1 = new ArrayList<Question>();
        List<Question> listLevel2 = new ArrayList<Question>();
        List<Question> listLevel3 = new ArrayList<Question>();
        List<Question> listLevel4 = new ArrayList<Question>();
        List<Question> listLevel5 = new ArrayList<Question>();

        for (Question q : questionList) {
            switch (q.getDifficultyLevel()) {
                case 1:
                    listLevel1.add(q);
                    break;
                case 2:
                    listLevel2.add(q);
                    break;
                case 3:
                    listLevel3.add(q);
                    break;
                case 4:
                    listLevel4.add(q);
                    break;
                case 5:
                    listLevel5.add(q);
                    break;
            }
        }

        questionsMap.put(1, listLevel1);
        questionsMap.put(2, listLevel2);
        questionsMap.put(3, listLevel3);
        questionsMap.put(4, listLevel4);
        questionsMap.put(5, listLevel5);
    }

    private void setUserLevelForCategory(Category category) {
        User user = (User) ((UserController) UserController.getSessionObject("userController")).getLoggedUser();
        UserCategories userCategory = controller.getUserCategory(user, category);
        if (userCategory == null) {
            currentLevel = STARTING_LEVEL;
        } else {
            currentLevel = userCategory.getLevel();
        }
    }

    private Question getRandomQuestion(int level) {
        List<Question> questionsByLevel = questionsMap.get(level);
        return questionsByLevel.get(random.nextInt(questionsByLevel.size()-1));
    }

    public void nextQuestion() {
        adaptLevel(checkAnswers());
        currentQuestion = getRandomQuestion(currentLevel);
    }

    public void startTest() {
        setUserLevelForCategory(selectedCategory);
        getQuestionsByCategory(selectedCategory);
        currentQuestion = getRandomQuestion(currentLevel);
        duringTestState = true;
        startState = false;
    }

    public void saveAdaptiveTest() {
    }

    public boolean checkAnswers() {
        boolean answer = true;
        List<Answer> correctAnswers = controller.getAnswersByQuestionId(currentQuestion.getId());
        for (int i = 0; i < correctAnswers.size(); i++) {
            if(correctAnswers.get(i).isStatus() != selectedAnswers.get(i).isStatus()){
                answer = false;
                break;
            }
        }
        return answer;
    }
    
    private void adaptLevel(boolean answer){
         if(answer){
            currentScore += currentQuestion.getDifficultyLevel();
            correctCounter++;
            if(correctCounter == 3){
                currentLevel++;
            }
        } else {
            wrongCounter++;
            if(wrongCounter == 3){
                currentLevel--;
            }
        }
    }
}
