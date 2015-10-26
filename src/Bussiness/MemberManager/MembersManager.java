/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness.MemberManager;

import closermeapp.Bussiness.Entities.Member;
import closermeapp.Bussiness.Interfaces.MemberManager;
import closermeapp.Data.FileManager.FilesManager;
import closermeapp.Data.Interfaces.FileManager;


/**
 * @author JoseJulio
 */
public class MembersManager implements MemberManager {

    @Override
    public void addMember(String name, String phone, String adress, String celPhone) {
        Member newMember = new Member(name, phone, adress, celPhone);

        saveToFile(newMember);

    }


    @Override
    public void addMemberWithMemberhip(String name, String phone, String adress, String celPhone, String membershipType, double discount) {
        Member newMember = new Member(name, phone, adress, celPhone);
        newMember.setMembershipWithDiscount(membershipType, discount);
        saveToFile(newMember);

    }

    @Override
    public void findMember(String name, String phone) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void saveToFile(Member newMember) {
        FileManager fileManager = new FilesManager();
        fileManager.saveMemberIntoFile(newMember);
    }

}
