package closermeapp.Data.DAOs;

import closermeapp.Bussiness.Entities.Member;
import closermeapp.Data.SessionGenerator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by André on 02/11/2015.
 */
public class MembersDAO {
    private Session session;
    private Transaction transaction;
    private static MembersDAO instance = null;

    private MembersDAO() {

    }

    public static MembersDAO getInstance() {
        if (instance == null) {
            instance = new MembersDAO();
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


    public int saveMember(Member member) {
        int id = 0;

        try {
            initOperation();
            id = (int) session.save(member);

            transaction.commit();
        } catch (HibernateException hibernateException) {
            ExceptionManagement(hibernateException);
            throw hibernateException;
        } finally {
            session.close();
        }
        return id;
    }

    public void updateMember(Member member) throws HibernateException {
        try {
            initOperation();
            session.update(member);
            transaction.commit();
        } catch (HibernateException hibernateException) {
            ExceptionManagement(hibernateException);
            throw hibernateException;
        } finally {
            session.close();
        }
    }



    public void deleteMember(Member member) throws HibernateException {
        try {
            initOperation();
            session.delete(member);
            transaction.commit();
        } catch (HibernateException hibernateException) {
            ExceptionManagement(hibernateException);
            throw hibernateException;
        } finally {
            session.close();
        }
    }


    public Member getMember(int id) throws HibernateException {
        Member member = null;

        try {
            initOperation();
            member = (Member) session.get(Member.class, id);
        } finally {
            session.close();
        }
        return member;
    }

    public List<Member> MemberList() throws HibernateException {
        List memberList = null;

        try {
            initOperation();

            memberList = session.createQuery("from Member ").list();
        } finally {
            session.close();
        }

        return memberList;
    }


}
