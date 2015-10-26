/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closermeapp.Bussiness.Entities;

import java.time.LocalDateTime;

/**
 * @author JoseJulio
 */
public class Membership {
    private String type;
    private double discount;
    private LocalDateTime expireDate;

    public Membership(String type, double discount) {
        this.type = type;
        this.discount = discount;

        expireDate = LocalDateTime.now().plusDays(7);
    }

    public String getType() {
        return type;
    }

    public double getDiscount() {
        return discount;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

}
