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
    private String typeMembership;
    private double discount;
    private LocalDateTime expireDate;

    public Membership(String typeMembership, double discount) {
        this.typeMembership = typeMembership;
        this.discount = discount;

        getExpireDate();
    }

    public String getTypeMembership() {
        return typeMembership;
    }

    public double getDiscount() {
        return discount;
    }

    public LocalDateTime getExpireDate() {
        switch (typeMembership) {
            case "Semanal":
                int diasSemana = 7;
                expireDate = LocalDateTime.now().plusDays(diasSemana);
                break;
            case "Mensual":
                int diasMes = 30;
                expireDate = LocalDateTime.now().plusDays(diasMes);
                break;
            case "Anual":
                int diasAnual = 365;
                expireDate = LocalDateTime.now().plusDays(diasAnual);
            default:
                int diasExtras = 0;
                expireDate = LocalDateTime.now().plusDays(diasExtras);
        }
        return expireDate;
    }


}
