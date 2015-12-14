package closermeapp.Data.DAOs;

import closermeapp.Bussiness.Entities.Enterprise;
import org.hibernate.Hibernate;

import java.util.ArrayList;

/**
 * Created by André on 05/12/2015.
 */
public class EnterpriseDAO extends AbstractDAO<Enterprise> {
    private static final EnterpriseDAO enterpriseDAO = new EnterpriseDAO();

    private EnterpriseDAO() {

    }

    public static EnterpriseDAO GetInstance() {

        return enterpriseDAO;
    }

    @Override
    public void add(Enterprise enterprise) {
        saveEntity(enterprise);
    }

    @Override
    public void delete(Enterprise enterprise) {
        deleteEntity(enterprise);
    }

    @Override
    public void update(Enterprise enterprise) {
        updateEntity(enterprise);
    }

    @Override
    public Object get(int objectId) {
        Enterprise enterprise = null;

        try {
            openSession();

            enterprise = (Enterprise) session.get(Enterprise.class, objectId);
        } finally {
            session.close();
        }
        return enterprise;
    }

    @Override
    public ArrayList<Enterprise> getList() {
        ArrayList<Enterprise> enterpriseList = null;

        try {
            openSession();

            enterpriseList = (ArrayList) session.createQuery("from Enterprise ").list();

            for (Enterprise enterprise : enterpriseList) {
                Hibernate.initialize(enterprise.getEmployeeList());
            }
        } finally {
            session.close();
        }

        return enterpriseList;
    }
}
