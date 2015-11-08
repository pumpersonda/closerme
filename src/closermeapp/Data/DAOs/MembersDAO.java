package closermeapp.Data.DAOs;

import closermeapp.Bussiness.Entities.Member;
import closermeapp.Data.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by André on 02/11/2015.
 */
public class MembersDAO {
    private Session sesion;
    private Transaction tx;
    private static MembersDAO instance = null;

    private MembersDAO() {

    }

    public static MembersDAO getInstance() {
        if (instance == null) {
            instance = new MembersDAO();
        }
        return instance;
    }

    /**
     * @throws HibernateException
     */
    private void initOperation() throws HibernateException {
        HibernateUtil util = new HibernateUtil();
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    /**
     * @param hibernateException
     * @throws HibernateException
     */
    private void ExceptionManagment(HibernateException hibernateException) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Ocurrio un error en la capa de acceso a datos", hibernateException);
    }

    /**
     * @param member
     * @return
     */
    public int saveMember(Member member) {
        int id = 0;

        try {
            initOperation();
            id = (int) sesion.save(member);

            tx.commit();
        } catch (HibernateException he) {
            ExceptionManagment(he);
            throw he;
        } finally {
            sesion.close();
        }
        return id;
    }

    /**
     * @param member
     * @throws HibernateException
     */
    public void updateMember(Member member) throws HibernateException {
        try {
            initOperation();
            sesion.update(member);
            tx.commit();
        } catch (HibernateException he) {
            ExceptionManagment(he);
            throw he;
        } finally {
            sesion.close();
        }
    }


    /**
     * @param member
     * @throws HibernateException
     */
    public void deleteMember(Member member) throws HibernateException {
        try {
            initOperation();
            sesion.delete(member);
            tx.commit();
        } catch (HibernateException he) {
            ExceptionManagment(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    /**
     * @param id
     * @return
     * @throws HibernateException
     */
    public Member getMember(int id) throws HibernateException {
        Member member = null;

        try {
            initOperation();
            member = (Member) sesion.get(Member.class, id);
        } finally {
            sesion.close();
        }
        return member;
    }

    /**
     * @return
     * @throws HibernateException
     */
    public List<Member> MemberList() throws HibernateException {
        List memberList = null;

        try {
            initOperation();

            memberList = sesion.createQuery("from Member ").list();
        } finally {
            sesion.close();
        }

        return memberList;
    }


}
