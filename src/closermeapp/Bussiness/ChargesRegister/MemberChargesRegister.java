package closermeapp.Bussiness.ChargesRegister;

import closermeapp.Bussiness.Entities.Member;

/**
 * Created by André on 12/12/2015.
 */
public class MemberChargesRegister {
    private int registerId;
    private String memberName;
    private String memberPhone;
    private String memberCellphone;
    private String memberAddress;
    private String membershipType;
    private double totalCharge;
    private String membershipStarDate;

    public MemberChargesRegister() {
    }

    public MemberChargesRegister(Member member, double totalCharge) {
        setRegisterId(member.getId());
        setMemberName(member.getName());
        setMemberPhone(member.getPhone());
        setMemberCellphone(member.getCellphone());
        setMemberAddress(member.getAddress());
        setMembershipType(member.getMembership().getMembershipType());
        setTotalCharge(totalCharge);
        setMembershipStarDate(member.getMembership().getStartDate());
    }

    public void setRegisterId(int id) {
        this.registerId = id;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    public void setMemberCellphone(String memberCellphone) {
        this.memberCellphone = memberCellphone;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public void setMembershipStarDate(String membershipStarDate) {
        this.membershipStarDate = membershipStarDate;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public void setTotalCharge(double totalCharge) {
        this.totalCharge = totalCharge;
    }

    public int getRegisterId() {
        return registerId;
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public String getMemberCellphone() {
        return memberCellphone;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public String getMembershipStarDate() {
        return membershipStarDate;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public double getTotalCharge() {
        return totalCharge;
    }

    @Override
    public String toString() {
        return "Name:  " + getMemberName() +
                "  Address:  " + getMemberAddress() +
                "  Phone:  " + getMemberPhone() +
                "  Cellphone:  " + getMemberCellphone() +
                "  Membership Type:  " + getMembershipType() +
                "  total Charge:  " + getTotalCharge();
    }
}
