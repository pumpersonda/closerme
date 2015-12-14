package closermeapp.Data.DAOs;

import closermeapp.Bussiness.ChargesRegister.MemberChargesRegister;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by André on 12/12/2015.
 */
public class MemberChargesRegisterDAO extends AbstractDAO<MemberChargesRegister> {
    private static final MemberChargesRegisterDAO memberChargeRegisterDAO = new MemberChargesRegisterDAO();

    private MemberChargesRegisterDAO() {

    }

    public static MemberChargesRegisterDAO GetInstance() {
        return memberChargeRegisterDAO;
    }

    @Override
    public void add(MemberChargesRegister memberChargesRegister) {
        saveEntity(memberChargesRegister);
    }

    @Override
    public void delete(MemberChargesRegister memberChargesRegister) {
        deleteEntity(memberChargesRegister);
    }

    @Override
    public void update(MemberChargesRegister memberChargesRegister) {
        updateEntity(memberChargesRegister);
    }

    @Override
    public Object get(int objectId) {
        return null;
    }

    @Override
    public ArrayList<MemberChargesRegister> getList() {
        ArrayList membershipList = null;

        try {
            openSession();

            membershipList = (ArrayList) session.createQuery("FROM MemberChargesRegister").list();
        } finally {
            session.close();
        }

        return membershipList;

    }

    public List<MemberChargesRegister> getInSpecificDate(String startDate, String endDate) {

        List membershipList = null;

        try {
            openSession();

            Criteria criteria = session.createCriteria(MemberChargesRegister.class);
            criteria.add(Restrictions.ge("membershipStarDate", startDate));
            criteria.add(Restrictions.lt("membershipStarDate", endDate));
            membershipList = criteria.list();

        } finally {
            session.close();
        }

        return membershipList;

    }
}
