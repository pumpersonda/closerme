/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closermeapp.Bussiness.MemberManager;

import closermeapp.Bussiness.Entities.Member;
import closermeapp.Data.DAOs.MembersDAO;

import java.util.ArrayList;

public class MembersManager {
    private MembershipManager membershipManager;
    private MembersDAO membersDAO;
    private static MembersManager membersManager;

    private MembersManager() {
        this.membershipManager = MembershipManager.getMembershipManager();
        this.membersDAO = MembersDAO.getMembersDAO();
    }

    public static MembersManager getMembersManager() {
        if (membersManager == null) {
            membersManager = new MembersManager();
        }
        return membersManager;
    }

    public Member createMember(
            String name,
            String phone,
            String address,
            String cellphone,
            String membershipNameType,
            double discount
    ) {
        Member member = new Member(name, phone, address, cellphone);
        this.membershipManager.addMembershipToMember(member, membershipNameType, discount);
        return member;
    }

    public void addMember(Member member) {
        saveMember(member);
    }


    public void deleteMember(Member member) {
        membersDAO.delete(member);
    }

    public Member getMember(int memberId) {
        Member member = (Member) this.membersDAO.get(memberId);
        return member;
    }

    public ArrayList<Member> getMemberList() {
        ArrayList<Member> memberList;
        memberList = this.membersDAO.getList();

        return memberList;
    }

    private void saveMember(Member member) {
        this.membersDAO.add(member);
    }


}
