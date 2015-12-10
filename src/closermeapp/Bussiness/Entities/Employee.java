/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closermeapp.Bussiness.Entities;


import java.io.Serializable;

public class Employee extends Visitor implements Serializable {
    private int id;
    private String role;

    public Employee(String name, String phone, String role) {
        super(name, phone);
        this.role = role;
    }

    public Employee() {
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public int getId() {
        return id;
    }


}
