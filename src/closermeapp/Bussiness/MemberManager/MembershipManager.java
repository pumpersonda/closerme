package closermeapp.Bussiness.MemberManager;

import closermeapp.Bussiness.DateManager.DateManager;
import closermeapp.Bussiness.Entities.Enterprise;
import closermeapp.Bussiness.Entities.Member;
import closermeapp.Bussiness.Entities.Membership;
import closermeapp.Bussiness.Entities.MembershipType;
import closermeapp.Data.DAOs.MembershipDAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by André on 28/11/2015.
 */
public class MembershipManager {
    private static final MembershipManager membershipManager = new MembershipManager();
    private MembershipDAO membershipDAO;
    private Member member;
    private Enterprise enterprise;

    private MembershipManager() {
        membershipDAO = MembershipDAO.GetInstance();
    }

    public static MembershipManager GetInstance() {
        return membershipManager;
    }

    public void addMembershipToMember(Member member, String membershipNameType) {
        setMember(member);

        MembershipType membershipType = getMembershipType(membershipNameType);
        Membership membership = new Membership(membershipType);

        setMembershipData(membership, membershipType);
        getMember().setMembership(membership);
        saveMembership(membership);
    }

    public void addMembershipToEnterprise(Enterprise enterprise, String membershipNameType) {
        setEnterprise(enterprise);

        MembershipType membershipType = getMembershipType(membershipNameType);
        Membership membership = new Membership(membershipType);

        setMembershipData(membership, membershipType);
        getEnterprise().setMembership(membership);
        saveMembership(membership);
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


    private void setMembershipData(Membership membership, MembershipType membershipType) {

        double membershipCost = membershipType.getMembershipCost();
        membership.setCosts(membershipCost);

        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        membership.setStartDate(today);

        int membershipDays = membershipType.getCurrentDays();
        DateManager dateManager = DateManager.getInstance();
        String expireDate = dateManager.getFutureDate(membershipDays);

        membership.setExpireDate(expireDate);
    }


    private void saveMembership(Membership membership) {
        this.membershipDAO.add(membership);
    }

    private void setMember(Member member) {
        this.member = member;
    }

    private void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    private Member getMember() {
        return member;
    }

    private Enterprise getEnterprise() {
        return enterprise;
    }
}
