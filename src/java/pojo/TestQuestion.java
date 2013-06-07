package pojo;

public class TestQuestion  implements java.io.Serializable {


     private Integer id;
     private Question question;
     private Test test;

    public TestQuestion() {
    }

    public TestQuestion(Question question, Test test) {
       this.question = question;
       this.test = test;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Question getQuestion() {
        return this.question;
    }
    
    public void setQuestion(Question question) {
        this.question = question;
    }
    public Test getTest() {
        return this.test;
    }
    
    public void setTest(Test test) {
        this.test = test;
    }




}


