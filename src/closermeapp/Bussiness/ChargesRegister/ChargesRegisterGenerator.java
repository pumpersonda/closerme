package closermeapp.Bussiness.ChargesRegister;

import closermeapp.Bussiness.Entities.Enterprise;
import closermeapp.Bussiness.Entities.Member;
import closermeapp.Data.DAOs.EnterpriseChargesRegisterDAO;
import closermeapp.Data.DAOs.MemberChargesRegisterDAO;

/**
 * Created by André on 12/12/2015.
 */
public class ChargesRegisterGenerator {
    private MemberChargesRegister memberChargesRegister;
    private EnterpriseChargesRegister enterpriseChargesRegister;
    private MemberChargesRegisterDAO memberChargesRegisterDAO;
    private EnterpriseChargesRegisterDAO enterpriseChargesRegisterDAO;


    public ChargesRegisterGenerator() {

        this.memberChargesRegisterDAO = MemberChargesRegisterDAO.GetInstance();
        this.enterpriseChargesRegisterDAO = EnterpriseChargesRegisterDAO.GetInstance();


    }

    public void addMemberChargesRegister(Member member, double totalCost) {
        memberChargesRegister = new MemberChargesRegister(member, totalCost);
        saveMemberChargesRegister(memberChargesRegister);
    }

    public void addEnterpriseChargeRegister(Enterprise enterprise, double totalCost) {
        enterpriseChargesRegister = new EnterpriseChargesRegister(enterprise, totalCost);
        saveEnterpriseChargesRegister(enterpriseChargesRegister);
    }


    private void saveMemberChargesRegister(MemberChargesRegister memberChargesRegister) {
        memberChargesRegisterDAO.add(memberChargesRegister);
    }

    private void saveEnterpriseChargesRegister(EnterpriseChargesRegister enterpriseChargesRegister) {
        enterpriseChargesRegisterDAO.add(enterpriseChargesRegister);
    }


}
