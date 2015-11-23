package closermeapp.Data.DAOs;

import closermeapp.Bussiness.Entities.Member;
import org.hibernate.HibernateException;

import java.util.ArrayList;

/**
 * Created by André on 02/11/2015.
 */
public class MembersDAO extends AbstractDAO {
    private static MembersDAO membersDAO;

    private MembersDAO() {

    }

    public static MembersDAO getMembersDAO() {
        if (membersDAO == null) {
            membersDAO = new MembersDAO();
        }
        return membersDAO;
    }

    @Override
    public void add(Object object) {
        try {
            initOperation();
            session.save(object);

            transaction.commit();
        } catch (HibernateException hibernateException) {
            exceptionManagement(hibernateException);
            throw hibernateException;
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Object object) {
        try {
            initOperation();
            session.delete(object);
            transaction.commit();
        } catch (HibernateException hibernateException) {
            exceptionManagement(hibernateException);
            throw hibernateException;
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Object object) {
        try {
            initOperation();
            session.update(object);
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

        Member member = null;

        try {
            initOperation();

            member = (Member) session.get(Member.class, objectId);
        } finally {
            session.close();
        }
        return member;
    }

    @Override
    public ArrayList getList() {

        ArrayList memberList = null;

        try {
            initOperation();

            memberList = (ArrayList) session.createQuery("from Member ").list();
        } finally {
            session.close();
        }

        return memberList;
    }
}
