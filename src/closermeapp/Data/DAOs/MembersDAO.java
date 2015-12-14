package closermeapp.Data.DAOs;

import closermeapp.Bussiness.Entities.Member;

import java.util.ArrayList;

/**
 * Created by Andr� on 02/11/2015.
 */
public class MembersDAO extends AbstractDAO<Member> {
    private static final MembersDAO membersDAO = new MembersDAO();

    private MembersDAO() {

    }

    public static MembersDAO GetInstance() {

        return membersDAO;
    }

    @Override
    public void add(Member member) {
        saveEntity(member);
    }

    @Override
    public void delete(Member member) {
        deleteEntity(member);
    }

    @Override
    public void update(Member member) {
        updateEntity(member);
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
