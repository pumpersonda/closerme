/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closermeapp.Bussiness.Entities;

import java.io.Serializable;

public class Member extends Client implements Serializable {
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

    public void setId(int id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public int getId() {
        return id;
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
