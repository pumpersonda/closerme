package closermeapp.Data.DAOs;

import closermeapp.Data.SessionGenerator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

/**
 * Created by Andr� on 20/11/2015.
 */
public abstract class AbstractDAO<Entity> {
    protected Session session;
    protected Transaction transaction;

    protected void initOperation() throws HibernateException {
        session = SessionGenerator.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    protected void exceptionManagement(HibernateException hibernateException) throws HibernateException {
        transaction.rollback();
        throw new HibernateException("Ocurrio un error en la capa de acceso a datos", hibernateException);
    }

    public abstract void add(Entity entity);

    public abstract void delete(Entity entity);

    public abstract void update(Entity entity);

    public abstract Object get(int objectId);

    public abstract ArrayList<?> getList();


}
