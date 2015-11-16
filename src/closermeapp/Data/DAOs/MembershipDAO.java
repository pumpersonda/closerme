package closermeapp.Data.DAOs;

import closermeapp.Bussiness.Entities.Membership;
import closermeapp.Data.SessionGenerator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by André on 05/11/2015.
 */
public class MembershipDAO {
    private Session session;
    private Transaction transaction;
    private static MembershipDAO instance = null;

    private MembershipDAO() {

    }

    public static MembershipDAO getInstance() {
        if (instance == null) {
            instance = new MembershipDAO();
        }
        return instance;
    }


    private void initOperation() throws HibernateException {
        session = SessionGenerator.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }


    private void ExceptionManagement(HibernateException hibernateException) throws HibernateException {
        transaction.rollback();
        throw new HibernateException("Ocurrio un error en la capa de acceso a datos", hibernateException);
    }


    public int saveMembership(Membership membership) {
        int id = 0;

        try {
            initOperation();
            id = (int) session.save(membership);

            transaction.commit();
        } catch (HibernateException hibernateException) {
            ExceptionManagement(hibernateException);
            throw hibernateException;
        } finally {
            session.close();
        }
        return id;
    }


    public void updateMembership(Membership membership) throws HibernateException {
        try {
            initOperation();
            session.update(membership);
            transaction.commit();
        } catch (HibernateException hibernateException) {
            ExceptionManagement(hibernateException);
            throw hibernateException;
        } finally {
            session.close();
        }
    }


    public void deleteMembership(Membership membership) throws HibernateException {
        try {
            initOperation();
            session.delete(membership);
            transaction.commit();
        } catch (HibernateException hibernateException) {
            ExceptionManagement(hibernateException);
            throw hibernateException;
        } finally {
            session.close();
        }
    }

    public Membership getMembership(int id) throws HibernateException {
        Membership member = null;

        try {
            initOperation();
            member = (Membership) session.get(Membership.class, id);
        } finally {
            session.close();
        }
        return member;
    }

    public List<Membership> MembershipList() throws HibernateException {
        List membershipList = null;

        try {
            initOperation();

            membershipList = session.createQuery("FROM Membership").list();
        } finally {
            session.close();
        }

        return membershipList;
    }
}
