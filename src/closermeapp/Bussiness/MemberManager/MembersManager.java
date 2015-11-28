/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closermeapp.Bussiness.MemberManager;

import closermeapp.Bussiness.Entities.Member;
import closermeapp.Bussiness.Entities.Membership;
import closermeapp.Data.DAOs.MembersDAO;
import closermeapp.Data.DAOs.MembershipDAO;

import java.util.ArrayList;

public class MembersManager {
    private MembersDAO membersDAO;
    private MembershipDAO membershipDAO;
    private static MembersManager membersManager;

    private MembersManager() {
        membersDAO = MembersDAO.getMembersDAO();
        membershipDAO = MembershipDAO.getMembershipDAO();
    }

    public static MembersManager getMembersManager() {
        if (membersManager == null) {
            membersManager = new MembersManager();
        }
        return membersManager;
    }


    public void addMember(
            String name,
            String phone,
            String address,
            String cellphone,
            String membershipType,
            double discount
    ) {
        Member newMember = new Member(name, phone, address, cellphone);
        addMembership(newMember, membershipType, discount);
        saveMember(newMember);
    }


    public void findMember(String name, String phone) {
        //To change body of generated methods, choose Tools | Templates.
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteMember(Member member) {
        membersDAO.delete(member);
    }

    public Member getMember(int memberId) {
        Member member = (Member) this.membersDAO.get(memberId);
        return member;
    }

    private void addMembership(Member newMember, String membershipType, double discount) {
        Membership membership = new Membership(membershipType, discount);
        newMember.setMembership(membership);
    }

    private void saveMember(Member newMember) {
        this.membershipDAO.add(newMember.getMembership());
        this.membersDAO.add(newMember);
    }

    public ArrayList getMemberList() {
        ArrayList<Member> memberList;
        memberList = this.membersDAO.getList();

        return memberList;
    }

}
