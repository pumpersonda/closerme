/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness.Entities;

/**
 * @author JoseJulio
 */
public class Member extends Visitor {

    private String adress;
    private String celphone;
    private Membership membership;

    public Member(String name, String phone, String adress, String celphone) {
        super(name, phone);
    }

    public void setMembership(String type) {
        membership = new Membership(type, 0);
    }

    public void setMembershipWithDiscount(String type, double discount) {
        membership = new Membership(type, discount);
    }

    public String getAdress() {
        return adress;
    }

    public String getCelphone() {
        return celphone;
    }

    public Membership getMembership() {
        return membership;
    }

}
