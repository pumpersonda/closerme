/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness.Interfaces;

/**
 * @author JoseJulio
 */
public interface MemberManager {

    void addMember(String name, String phone, String adress, String celPhone);

    void addMemberWithMemberhip(String name, String phone, String adress, String celPhone, String membershipType, double discount);

    void findMember(String name, String phone);

}
