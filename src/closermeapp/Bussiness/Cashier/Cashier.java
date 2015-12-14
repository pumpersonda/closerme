package closermeapp.Bussiness.Cashier;

import closermeapp.Bussiness.ChargesRegister.ChargesRegisterGenerator;
import closermeapp.Bussiness.Entities.Enterprise;
import closermeapp.Bussiness.Entities.Member;

/**
 * Created by André on 12/12/2015.
 */
public class Cashier {
    private static final Cashier cashier = new Cashier();
    private ChargesRegisterGenerator chargesRegisterGenerator;


    private Cashier() {
        this.chargesRegisterGenerator = new ChargesRegisterGenerator();
    }

    public static Cashier getInstance() {
        return cashier;
    }

    public double chargeTheMember(Member member, double discount) {
        double membershipCost = member.getMembership().getCosts();
        double totalCost = membershipCost - discount;

        getChargesRegisterGenerator().addMemberChargesRegister(member, totalCost);
        return totalCost;
    }

    public double chargeTheEnterprise(Enterprise enterprise) {
        double membershipCost = enterprise.getMembership().getCosts();
        int employeesNumber = enterprise.getEmployeeList().size();
        double totalCost = membershipCost * employeesNumber;
        getChargesRegisterGenerator().addEnterpriseChargeRegister(enterprise, totalCost);
        return totalCost;
    }

    public void chargeThevent() {

    }

    public ChargesRegisterGenerator getChargesRegisterGenerator() {
        return chargesRegisterGenerator;
    }

}
