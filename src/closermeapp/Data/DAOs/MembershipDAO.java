package closermeapp.Data.DAOs;

import closermeapp.Bussiness.Entities.Membership;
import closermeapp.Data.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by André on 05/11/2015.
 */
public class MembershipDAO {
    private Session sesion;
    private Transaction tx;
    private static MembershipDAO instance = null;

    private MembershipDAO() {

    }

    public static MembershipDAO getInstance() {
        if (instance == null) {
            instance = new MembershipDAO();
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
     * @param membership
     * @return
     */
    public int saveMembership(Membership membership) {
        int id = 0;

        try {
            initOperation();
            id = (int) sesion.save(membership);

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
     * @param membership
     * @throws HibernateException
     */
    public void updateMembership(Membership membership) throws HibernateException {
        try {
            initOperation();
            sesion.update(membership);
            tx.commit();
        } catch (HibernateException he) {
            ExceptionManagment(he);
            throw he;
        } finally {
            sesion.close();
        }
    }


    /**
     * @param membership
     * @throws HibernateException
     */
    public void deleteMembership(Membership membership) throws HibernateException {
        try {
            initOperation();
            sesion.delete(membership);
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
    public Membership getMembership(int id) throws HibernateException {
        Membership member = null;

        try {
            initOperation();
            member = (Membership) sesion.get(Membership.class, id);
        } finally {
            sesion.close();
        }
        return member;
    }

    /**
     * @return
     * @throws HibernateException
     */
    public List<Membership> MembershipList() throws HibernateException {
        List membershipList = null;

        try {
            initOperation();

            membershipList = sesion.createQuery("FROM membership").list();
        } finally {
            sesion.close();
        }

        return membershipList;
    }
}
