/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closermeapp.Bussiness.Interfaces;

/**
 * @author JoseJulio
 */
public interface MemberManager {

    void addMember(String name, String phone, String address, String cellphone, String membershipType, double discount);

    void findMember(String name, String phone);

}
