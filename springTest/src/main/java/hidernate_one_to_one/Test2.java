package hidernate_one_to_one;

import hidernate_one_to_one.entity.Detail;
import hidernate_one_to_one.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test2 {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Employee.class).addAnnotatedClass(Detail.class).buildSessionFactory();

        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();

            session.beginTransaction();
            Detail detail = session.get(Detail.class, 3);
            System.out.println(detail.getEmployee());
            session.delete(detail);


            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            session.close();
            sessionFactory.close();
        }
    }
}
