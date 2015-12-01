package closermeapp.Data.DAOs;

import closermeapp.Bussiness.Entities.Membership;

import java.util.ArrayList;

/**
 * Created by Andr� on 05/11/2015.
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
        String query = "add";
        enquire(query, membership);
    }

    @Override
    public void delete(Membership membership) {
        String query = "delete";
        enquire(query, membership);
    }

    @Override
    public void update(Membership membership) {
        String query = "update";
        enquire(query, membership);
    }

    @Override
    public Object get(int objectId) {
        Membership member = null;

        try {
            openSession();
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
            openSession();

            membershipList = (ArrayList) session.createQuery("FROM Membership").list();
        } finally {
            session.close();
        }

        return membershipList;
    }
}
