package closermeapp.Bussiness.Entities;

/**
 * Created by André on 28/11/2015.
 */
public enum Costs {
    SEMANAL("Semanal", 100),
    MENSUAL("Mensual", 200),
    ANUAL("Anual", 300);

    private final String membershipType;
    private final int membershipCost;

    Costs(String membershipType, int membershipCost) {
        this.membershipType = membershipType;
        this.membershipCost = membershipCost;
    }

    public int getMembershipCost() {
        return membershipCost;
    }

    public String getMembershipType() {
        return membershipType;
    }
}
