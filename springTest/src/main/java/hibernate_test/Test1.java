package hibernate_test;

import hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test1 {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Employee.class).buildSessionFactory();


        try {
            Session session = sessionFactory.getCurrentSession();
            Employee employee1 = new Employee("Alex", "Jobs", "Dr.dre", 300);
            session.beginTransaction();
            session.save(employee1);
            session.getTransaction().commit();
        }
        finally {
            sessionFactory.close();
        }
    }
}
