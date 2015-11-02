/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closermeapp.Bussiness.Entities;

/**
 * @author JoseJulio
 */
public class Member extends Visitor {

    private String address;
    private String cellphone;
    private Membership membership;

    public Member(String name, String phone, String address, String cellphone) {
        super(name, phone);
        this.address = address;
        this.cellphone = cellphone;
    }

    public void setMembership(String membershipType, double discount) {
        membership = new Membership(membershipType, discount);
    }

    public String getAddress() {
        return address;
    }

    public String getCellphone() {
        return cellphone;
    }

    public Membership getMembership() {
        return membership;
    }

}
