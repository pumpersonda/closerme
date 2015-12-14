package closermeapp.Data.DAOs;

import closermeapp.Bussiness.ChargesRegister.EnterpriseChargesRegister;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by André on 12/12/2015.
 */
public class EnterpriseChargesRegisterDAO extends AbstractDAO<EnterpriseChargesRegister> {
    private static final EnterpriseChargesRegisterDAO enterpriseChargesRegisterDAO = new EnterpriseChargesRegisterDAO();

    private EnterpriseChargesRegisterDAO() {

    }

    public static EnterpriseChargesRegisterDAO GetInstance() {
        return enterpriseChargesRegisterDAO;
    }

    @Override
    public void add(EnterpriseChargesRegister enterpriseChargesRegister) {
        saveEntity(enterpriseChargesRegister);
    }

    @Override
    public void delete(EnterpriseChargesRegister enterpriseChargesRegister) {
        deleteEntity(enterpriseChargesRegister);
    }

    @Override
    public void update(EnterpriseChargesRegister enterpriseChargesRegister) {
        updateEntity(enterpriseChargesRegister);
    }

    @Override
    public Object get(int objectId) {
        return null;
    }

    @Override
    public ArrayList<?> getList() {
        return null;
    }

    public List<EnterpriseChargesRegister> getInSpecificDate(String startDate, String endDate) {

        List membershipList = null;

        try {
            openSession();

            Criteria criteria = session.createCriteria(EnterpriseChargesRegister.class);
            criteria.add(Restrictions.ge("membershipStarDate", startDate));
            criteria.add(Restrictions.lt("membershipStarDate", endDate));
            membershipList = criteria.list();

        } finally {
            session.close();
        }

        return membershipList;

    }
}
