package controller;

import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
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

    public List getDomains() {
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

    public Integer getUser(String name, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Integer result = null;
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from User where name = ? and password = ?");
            query.setParameter(0, name);
            query.setParameter(1, password);
            if(query.list().size() == 1){
                result = 1;
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }
}
