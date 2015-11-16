package closermeapp.Data;


import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionGenerator {
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException hibernateExcepcion) {
            System.err.println("Ocurrió un error en la inicialización de la SessionFactory: " + hibernateExcepcion);
            throw new ExceptionInInitializerError(hibernateExcepcion);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}