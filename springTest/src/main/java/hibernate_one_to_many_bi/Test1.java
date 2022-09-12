package hibernate_one_to_many_bi;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test1 {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Employee.class).addAnnotatedClass(Detail.class).buildSessionFactory();

        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
//            Employee employee = new Employee("Oleg", "Gogi", "Sells", 2000);
//            Detail detail = new Detail("Moskov", "905788545", "ww444@mail.ru");
//            employee.setEmpDetail(detail);

            session.beginTransaction();

            Employee employee = session.get(Employee.class, 2);
            session.delete(employee);

            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            session.close();
            sessionFactory.close();
        }
    }
}
