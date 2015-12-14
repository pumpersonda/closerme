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
    private static final MembersManager membersManager = new MembersManager();
    private MembershipManager membershipManager;
    private MembersDAO membersDAO;

    private MembersManager() {
        this.membershipManager = MembershipManager.GetInstance();
        this.membersDAO = MembersDAO.GetInstance();
    }

    public static MembersManager getMembersManager() {
        return membersManager;
    }

    public Member createMember(
            String name,
            String phone,
            String address,
            String cellphone,
            String membershipNameType
    ) {
        Member member = new Member(name, phone, address, cellphone);
        this.membershipManager.addMembershipToMember(member, membershipNameType);
        return member;
    }

    public void addMember(Member member) {
        saveMember(member);
    }


    public void deleteMember(Member member) {
        membersDAO.delete(member);
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
