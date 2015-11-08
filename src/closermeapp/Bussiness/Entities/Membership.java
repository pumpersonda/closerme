/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closermeapp.Bussiness.Entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author JoseJulio
 */

public class Membership implements Serializable {
    private int membershipNumber;
    private String membershipType;
    private double discount;
    private String startDate;
    private String expireDate;

    public Membership(String typeMembership, double discount) {
        this.membershipType = typeMembership;
        this.discount = discount;
        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        setStartDate(today);
        setNewExpireDate();
    }


    public Membership() {
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    private void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setNewExpireDate() {

        switch (membershipType) {
            case "Semanal":
                int diasSemana = 7;
                this.expireDate = LocalDateTime.now().plusDays(diasSemana).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
                break;
            case "Mensual":
                int diasMes = 30;
                this.expireDate = LocalDateTime.now().plusDays(diasMes).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
                break;
            case "Anual":
                int diasAnual = 365;
                this.expireDate = LocalDateTime.now().plusDays(diasAnual).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
            default:
                int diasExtras = 0;
                this.expireDate = LocalDateTime.now().plusDays(diasExtras).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        }
    }

    private void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public int getMembershipNumber() {
        return membershipNumber;
    }

    public void setMembershipNumber(int membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

}
