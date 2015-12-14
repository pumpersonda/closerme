package closermeapp.Bussiness.ChargesRegister;

import closermeapp.Bussiness.Entities.Enterprise;
import closermeapp.Bussiness.Entities.Event;
import closermeapp.Bussiness.Entities.Member;
import closermeapp.Data.DAOs.EnterpriseChargesRegisterDAO;
import closermeapp.Data.DAOs.EventChargeRegisterDAO;
import closermeapp.Data.DAOs.MemberChargesRegisterDAO;

/**
 * Created by André on 12/12/2015.
 */
public class ChargesRegisterGenerator {
    private MemberChargesRegister memberChargesRegister;
    private EnterpriseChargesRegister enterpriseChargesRegister;
    private EventChargeRegister eventChargeRegister;
    private MemberChargesRegisterDAO memberChargesRegisterDAO;
    private EnterpriseChargesRegisterDAO enterpriseChargesRegisterDAO;
    private EventChargeRegisterDAO eventChargeRegisterDAO;

    public ChargesRegisterGenerator() {

        this.memberChargesRegisterDAO = MemberChargesRegisterDAO.GetInstance();
        this.enterpriseChargesRegisterDAO = EnterpriseChargesRegisterDAO.GetInstance();
        this.eventChargeRegisterDAO = EventChargeRegisterDAO.getEventChargeRegisterDAO();


    }

    public void addMemberChargesRegister(Member member, double totalCost) {
        memberChargesRegister = new MemberChargesRegister(member, totalCost);
        saveMemberChargesRegister(memberChargesRegister);
    }

    public void addEnterpriseChargeRegister(Enterprise enterprise, double totalCost) {
        enterpriseChargesRegister = new EnterpriseChargesRegister(enterprise, totalCost);
        saveEnterpriseChargesRegister(enterpriseChargesRegister);
    }

    public void addEventChargeRegister(Event event, double totalCost) {
        eventChargeRegister = new EventChargeRegister( event, totalCost );
        saveEventChargeRegister( eventChargeRegister );
    }

    private void saveMemberChargesRegister(MemberChargesRegister memberChargesRegister) {
        memberChargesRegisterDAO.add(memberChargesRegister);
    }

    private void saveEnterpriseChargesRegister(EnterpriseChargesRegister enterpriseChargesRegister) {
        enterpriseChargesRegisterDAO.add(enterpriseChargesRegister);
    }

    private void saveEventChargeRegister(EventChargeRegister eventChargeRegister) {
        eventChargeRegisterDAO.add( eventChargeRegister );
    }

}
