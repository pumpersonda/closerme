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
    private String startDate;
    private String expireDate;
    private double costs;

    public Membership(MembershipType membershipType) {
        this.membershipType = membershipType.getMembershipName();
        setMembershipData(membershipType);
    }

    public Membership() {
    }

    public void setMembershipNumber(int membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
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

    public String getStartDate() {
        return startDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public double getCosts() {
        return costs;
    }

    private String getFormattedDate(int membershipType) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String expire = LocalDateTime.now().plusDays(membershipType).format(formatter);
        return expire;
    }

    private void setMembershipData(MembershipType membershipType) {
        double membershipCost = membershipType.getMembershipCost();
        setCosts(membershipCost);

        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        setStartDate(today);

        int membershipDays = membershipType.getCurrentDays();
        String expireDate = getFormattedDate(membershipDays);
        setExpireDate(expireDate);
    }

}
