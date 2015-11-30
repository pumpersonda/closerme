package closermeapp.Bussiness.Entities;

/**
 * Created by André on 28/11/2015.
 */
public enum MembershipType {
    WEEKLY("Semanal", 7, 100),
    MONTHLY("Mensual", 30, 200),
    ANNUAL("Anual", 365, 300);


    private final String membershipName;
    private final int membershipCost;
    private final int currentDays;

    MembershipType(String membershipName, int currentDays, int membershipCost) {
        this.membershipName = membershipName;
        this.membershipCost = membershipCost;
        this.currentDays = currentDays;
    }

    public int getMembershipCost() {
        return membershipCost;
    }

    public String getMembershipName() {
        return membershipName;
    }

    public int getCurrentDays() {
        return currentDays;
    }
}
