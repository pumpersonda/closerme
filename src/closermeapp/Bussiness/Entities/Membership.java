/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closermeapp.Bussiness.Entities;

import java.io.Serializable;

public class Membership implements Serializable {
    private int membershipNumber;
    private String membershipType;
    private String startDate;
    private String expireDate;
    private double costs;

    public Membership(MembershipType membershipType) {
        this.membershipType = membershipType.getMembershipName();
    }

    public Membership() {
    }

    public void setMembershipNumber(int membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setExpireDate(String expireDate) {
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

}
