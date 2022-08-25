package hibernate_test;

import hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Test4 {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Employee.class).buildSessionFactory();


        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

           session.createQuery("update Employee set salary = 1000 where name = 'Alex'").executeUpdate();

            session.getTransaction().commit();

            System.out.println("Don!");
        }
        finally {
            sessionFactory.close();
        }
    }
}
