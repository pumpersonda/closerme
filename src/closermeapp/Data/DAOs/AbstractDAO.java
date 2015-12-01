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

    protected void openSession() throws HibernateException {
        session = SessionGenerator.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    protected void exceptionManagement(HibernateException hibernateException) throws HibernateException {
        transaction.rollback();
        throw new HibernateException("Ocurrio un error en la capa de acceso a datos", hibernateException);
    }

    protected abstract void add(Entity entity);

    protected abstract void delete(Entity entity);

    protected abstract void update(Entity entity);

    protected abstract Object get(int objectId);

    protected abstract ArrayList<?> getList();

    protected void enquire(String queryId, Entity entity) {
        try {
            openSession();

            switch (queryId) {
                case "add":
                    session.save(entity);
                    break;
                case "delete":
                    session.delete(entity);
                    break;
                case "update":
                    session.update(entity);
                    break;
            }
            transaction.commit();
        } catch (HibernateException hibernateException) {
            exceptionManagement(hibernateException);
            throw hibernateException;
        } finally {
            session.close();
        }
    }



}
