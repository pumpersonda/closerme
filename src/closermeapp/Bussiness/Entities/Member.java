/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closermeapp.Bussiness.Entities;

import java.io.Serializable;


public class Member extends Visitor implements Serializable {
    private int id;
    private String address;
    private String cellphone;
    private Membership membership;


    public Member(
            String name,
            String phone,
            String address,
            String cellphone
    ) {
        super(name, phone);
        this.address = address;
        this.cellphone = cellphone;
    }

    public Member() {
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setNewMembership(String membershipType, double discount) {
        Membership membership = new Membership(membershipType, discount);
        setMembership(membership);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private void setMembership(Membership membership) {
        this.membership = membership;
    }


}
