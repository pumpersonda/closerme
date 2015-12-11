package closermeapp.Bussiness.MemberManager;

import closermeapp.Bussiness.Entities.Enterprise;
import closermeapp.Bussiness.Entities.Member;
import closermeapp.Bussiness.Entities.Membership;
import closermeapp.Bussiness.Entities.MembershipType;
import closermeapp.Data.DAOs.MembershipDAO;

/**
 * Created by André on 28/11/2015.
 */
public class MembershipManager {
    private static MembershipManager membershipManager;
    private MembershipDAO membershipDAO;
    private Member member;
    private Enterprise enterprise;

    private MembershipManager() {
        membershipDAO = MembershipDAO.getMembershipDAO();
    }

    public static MembershipManager getMembershipManager() {
        if (membershipManager == null) {
            membershipManager = new MembershipManager();
        }
        return membershipManager;
    }

    public void addMembershipToMember(Member member, String membershipNameType, double discount) {
        setMember(member);

        MembershipType membershipType = getMembershipType(membershipNameType);
        Membership membership = new Membership(membershipType);

        setMembershipDiscount(membership, discount);
        getMember().setMembership(membership);
        saveMembership(membership);
    }

    public void addMembershipToEnterprise(Enterprise enterprise, String membershipNameType) {
        setEnterprise(enterprise);

        MembershipType membershipType = getMembershipType(membershipNameType);
        Membership membership = new Membership(membershipType);

        getEnterprise().setMembership(membership);
        saveMembership(membership);
    }

    public double getTotalMembershipCost() {
        int membershipId = this.getMember().getId();
        Membership membership = (Membership) membershipDAO.get(membershipId);

        return membership.getCosts();
    }


    private MembershipType getMembershipType(String membershipNameType) {
        MembershipType membershipType = null;
        switch (membershipNameType) {
            case "Semanal":
                membershipType = MembershipType.WEEKLY;
                break;
            case "Mensual":
                membershipType = MembershipType.MONTHLY;
                break;
            case "Anual":
                membershipType = MembershipType.ANNUAL;
                break;
        }
        return membershipType;
    }

    private void setMembershipDiscount(Membership membership, double discount) {
        double membershipCost = membership.getCosts() - discount;
        membership.setCosts(membershipCost);
    }

    private void saveMembership(Membership membership) {
        this.membershipDAO.add(membership);
    }


    private void setMember(Member member) {
        this.member = member;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    private Member getMember() {
        return member;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }
}
