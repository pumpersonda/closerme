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
    private static MembersManager instance;

    private MembersManager() {
        // Exists only to defeat instantiation.
    }

    public static MembersManager getInstance() {
        if (instance == null) {
            instance = new MembersManager();
        }
        return instance;
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
        newMember.setOneMembership(membershipType, discount);
    }


    private void saveMember(Member newMember) {
        MembershipDAO membershipDAO = MembershipDAO.getInstance();
        membershipDAO.saveMembership(newMember.getMembership());
        MembersDAO membersDAO = MembersDAO.getInstance();
        membersDAO.saveMember(newMember);
    }

}
