/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closermeapp.Bussiness.Entities;

/**
 * @author JoseJulio
 */
public class EventualVisitor extends Visitor {
    private String email;

    public EventualVisitor(String name, String phone, String email) {
        super(name, phone);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
