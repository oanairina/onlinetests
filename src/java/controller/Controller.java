package controller;

import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import pojo.Answer;
import pojo.Category;
import pojo.Domain;
import pojo.HibernateUtil;
import pojo.Question;
import pojo.User;

public class Controller implements Serializable {

    //Session session = null;
    public Controller() {
        //this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public Integer addElement(Object element) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Integer id = null;
        try {
            transaction = session.beginTransaction();
            id = (Integer) session.save(element);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return id;
    }

    public void removeElement(Object element) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(element);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /*------Domains-----*/
    public List<Domain> getDomains() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Domain> domainList = null;
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Domain");
            domainList = (List<Domain>) query.list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return domainList;
    }

    public Domain getDomainByName(String domainName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (Domain) session.createCriteria(Domain.class).
                    add(Restrictions.eq("name", domainName)).
                    uniqueResult();
        } finally {
            session.close();
        }
    }

    public void removeDomain(String name) {
        Domain domain = getDomainByName(name);
        if (domain != null) {
            removeElement(domain);
        }
    }

    /*------Category-----*/
    public List getCategories() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Category> categoryList = null;
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Category");
            categoryList = (List<Category>) query.list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return categoryList;
    }

    public List getCategories(Domain domain) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Category> categoryList = null;
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Category where domain_id =" + domain.getId());
            categoryList = (List<Category>) query.list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return categoryList;
    }

    public Category getCategoryByName(String categoryName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (Category) session.createCriteria(Category.class).
                    add(Restrictions.eq("name", categoryName)).
                    uniqueResult();
        } finally {
            session.close();
        }
    }

    public void removeCategory(String name) {
        Category category = getCategoryByName(name);
        if (category != null) {
            removeElement(category);
        }
    }

    /*------Questions-----*/
    public List getQuestions() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Question> questionsList = null;
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Question");
            questionsList = (List<Question>) query.list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return questionsList;
    }

    public List getQuestions(Category category) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Question> questionsList = null;
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Question where category_id=" + category.getId());
            questionsList = (List<Question>) query.list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return questionsList;
    }
    
       public Question getQuestionByID(int questionID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (Question) session.createCriteria(Question.class).
                    add(Restrictions.eq("id", questionID)).
                    uniqueResult();
        } finally {
            session.close();
        }
    }
    
      public void removeQuestion(int id) {
        Question question = getQuestionByID(id);
        if (question != null) {
            removeElement(question);
        }
      }

      
        /*------Answers-----*/
    public List getAnswers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Answer> answersList = null;
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Answer");
            answersList = (List<Answer>) query.list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return answersList;
    }

    public List<User> getUsersList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> usersList = null;
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from User");
            usersList = (List<User>) query.list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return usersList;
    }

    public User getUser(String name, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User userResult = null;
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from User where name = ? and password = ?");
            query.setParameter(0, name);
            query.setParameter(1, password);
            userResult = (User) query.list().get(0);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userResult;
    }

    public User getUserById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (User) session.createCriteria(User.class).
                    add(Restrictions.eq("id", id)).
                    uniqueResult();
        } finally {
            session.close();
        }
    }
}
