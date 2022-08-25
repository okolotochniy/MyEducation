package hibernate_test;

import hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test2 {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Employee.class).buildSessionFactory();


        try {
/*            Session session = sessionFactory.getCurrentSession();
            Employee employee1 = new Employee("Elena", "Petrovna", "Dr.dre", 300);
            session.beginTransaction();
            session.save(employee1);
            session.getTransaction().commit();*/

            System.out.println("Don!");

        //    int myId = employee1.getId();

            Session session1 = sessionFactory.getCurrentSession();
            session1.beginTransaction();
            Employee employee2 = session1.get(Employee.class, 1);
            session1.getTransaction().commit();
            System.out.println(employee2);
        }
        finally {
            sessionFactory.close();
        }
    }
}
