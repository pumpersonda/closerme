package closermeapp.Bussiness.DebtCollector;

import closermeapp.Bussiness.ChargesRegister.ChargesRegisterGenerator;
import closermeapp.Bussiness.Entities.Enterprise;
import closermeapp.Bussiness.Entities.Member;

/**
 * Created by André on 12/12/2015.
 */
public class DebtCollector {
    private static DebtCollector debtCollector;
    private ChargesRegisterGenerator chargesRegisterGenerator;


    private DebtCollector() {
        this.chargesRegisterGenerator = new ChargesRegisterGenerator();
    }

    public static DebtCollector getDebtCollector() {
        if (debtCollector == null) {
            debtCollector = new DebtCollector();
        }
        return debtCollector;
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
