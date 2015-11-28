package closermeapp.Data.DAOs;

import closermeapp.Bussiness.Entities.Membership;
import org.hibernate.HibernateException;

import java.util.ArrayList;

/**
 * Created by André on 05/11/2015.
 */
public class MembershipDAO extends AbstractDAO<Membership> {

    private static MembershipDAO membershipDAO;

    private MembershipDAO() {

    }

    public static MembershipDAO getMembershipDAO() {
        if (membershipDAO == null) {
            membershipDAO = new MembershipDAO();
        }
        return membershipDAO;
    }

    @Override
    public void add(Membership membership) {
        try {
            initOperation();
            session.save(membership);

            transaction.commit();
        } catch (HibernateException hibernateException) {
            exceptionManagement(hibernateException);
            throw hibernateException;
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Membership membership) {
        try {
            initOperation();
            session.delete(membership);
            transaction.commit();
        } catch (HibernateException hibernateException) {
            exceptionManagement(hibernateException);
            throw hibernateException;
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Membership membership) {
        try {
            initOperation();
            session.update(membership);
            transaction.commit();
        } catch (HibernateException hibernateException) {
            exceptionManagement(hibernateException);
            throw hibernateException;
        } finally {
            session.close();
        }
    }

    @Override
    public Object get(int objectId) {
        Membership member = null;

        try {
            initOperation();
            member = (Membership) session.get(Membership.class, objectId);
        } finally {
            session.close();
        }
        return member;
    }

    @Override
    public ArrayList<Membership> getList() {
        ArrayList membershipList = null;

        try {
            initOperation();

            membershipList = (ArrayList) session.createQuery("FROM Membership").list();
        } finally {
            session.close();
        }

        return membershipList;
    }
}
