/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closermeapp.Bussiness.Entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Membership implements Serializable {
    private int membershipNumber;
    private String membershipType;
    private double discount;
    private String startDate;
    private String expireDate;
    private double costs = 100.0;

    public Membership(String typeMembership, double discount) {
        this.membershipType = typeMembership;
        this.discount = discount;
        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        setStartDate(today);
        setNewExpireDate();
    }

    public Membership() {
    }

    public void setMembershipNumber(int membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    private void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    private void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public void setCosts(double costs) {
        this.costs = costs;
    }

    public int getMembershipNumber() {
        return membershipNumber;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public double getDiscount() {
        return discount;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public double getCosts() {
        return costs;
    }

    private void setNewExpireDate() {
        int membershipDays = 0;

        switch (membershipType) {
            case "Semanal":
                membershipDays = 7;
                break;
            case "Mensual":
                membershipDays = 30;
                break;
            case "Anual":
                membershipDays = 365;
        }
        setExpireDate(getFormattedDate(membershipDays));

    }

    private String getFormattedDate(int membershipType) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String expire = LocalDateTime.now().plusDays(membershipType).format(formatter);
        return expire;
    }

}
