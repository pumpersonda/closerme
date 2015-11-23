package closermeapp.Data.DAOs;

import closermeapp.Data.SessionGenerator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by André on 20/11/2015.
 */
public abstract class AbstractDAO {
    protected Session session;
    protected Transaction transaction;

    protected void initOperation() throws HibernateException {
        session = SessionGenerator.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    protected void ExceptionManagement(HibernateException hibernateException) throws HibernateException {
        transaction.rollback();
        throw new HibernateException("Ocurrio un error en la capa de acceso a datos", hibernateException);
    }

    public abstract void add(Object object);

    public abstract void delete(Object object);

    public abstract void update(Object object);

    public abstract Object get(int objectId);

    public abstract List getList();


}
