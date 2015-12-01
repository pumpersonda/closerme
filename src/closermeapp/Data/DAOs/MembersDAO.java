package closermeapp.Data.DAOs;

import closermeapp.Bussiness.Entities.Member;

import java.util.ArrayList;

/**
 * Created by Andr� on 02/11/2015.
 */
public class MembersDAO extends AbstractDAO<Member> {
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
    public void add(Member member) {
        String query = "add";
        enquire(query, member);
    }

    @Override
    public void delete(Member member) {
        String query = "delete";
        enquire(query, member);
    }

    @Override
    public void update(Member member) {
        String query = "update";
        enquire(query, member);
    }

    @Override
    public Object get(int objectId) {

        Member member = null;

        try {
            openSession();

            member = (Member) session.get(Member.class, objectId);
        } finally {
            session.close();
        }
        return member;
    }

    @Override
    public ArrayList<Member> getList() {

        ArrayList memberList = null;

        try {
            openSession();

            memberList = (ArrayList) session.createQuery("from Member ").list();
        } finally {
            session.close();
        }

        return memberList;
    }
}
