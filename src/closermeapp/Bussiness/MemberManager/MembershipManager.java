package closermeapp.Bussiness.MemberManager;

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

    private MembershipManager() {

        membershipDAO = MembershipDAO.getMembershipDAO();
    }

    public static MembershipManager getMembersManager() {
        if (membershipManager == null) {
            membershipManager = new MembershipManager();
        }
        return membershipManager;
    }

    public void addMembership(Member member, String membershipNameType, double discount) {
        MembershipType membershipType = getMembershipType(membershipNameType);
        Membership membership = new Membership(membershipType);

        member.setMembership(membership);

        setMembershipDiscount(membership, discount);
        saveMembership(member);
    }

    public double getTotalMembershipCost() {
        int membershipId = this.member.getId();
        Membership membership = (Membership) membershipDAO.get(membershipId);

        return membership.getCosts();
    }

    private MembershipType getMembershipType(String membershipNameType) {
        MembershipType membershipType = null;
        switch (membershipNameType) {
            case "Semanal":
                membershipType = MembershipType.SEMANAL;
                break;
            case "Mensual":
                membershipType = MembershipType.MENSUAL;
                break;
            case "Anual":
                membershipType = MembershipType.ANUAL;
                break;
        }
        return membershipType;
    }

    private void setMembershipDiscount(Membership membership, double discount) {
        double membershipCost = membership.getCosts() - discount;

        membership.setCosts(membershipCost);
    }

    private void saveMembership(Member member) {
        this.membershipDAO.add(member.getMembership());
    }


}
