/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closermeapp.Bussiness.MemberManager;

import closermeapp.Bussiness.Entities.Member;
import closermeapp.Data.DAOs.MembersDAO;
import closermeapp.Data.DAOs.MembershipDAO;


/**
 * @author JoseJulio
 */

public class MembersManager {
    private MembersDAO membersDAO = MembersDAO.getMembersDAO();
    private MembershipDAO membershipDAO = MembershipDAO.getMembershipDAO();
    private static MembersManager membersManager;

    private MembersManager() {
        // Exists only to defeat instantiation.
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

    private void addMembership(Member newMember, String membershipType, double discount) {
        newMember.createMembership(membershipType, discount);
    }

    private void saveMember(Member newMember) {
        this.membershipDAO.saveMembership(newMember.getMembership());
        this.membersDAO.saveMember(newMember);
    }

}
