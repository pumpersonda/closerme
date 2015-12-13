package closermeapp.Bussiness.Reports;

import closermeapp.Bussiness.ChargesRegister.EnterpriseChargesRegister;
import closermeapp.Bussiness.ChargesRegister.MemberChargesRegister;
import closermeapp.Bussiness.DateManager.DateManager;
import closermeapp.Data.DAOs.EnterpriseChargesRegisterDAO;
import closermeapp.Data.DAOs.MemberChargesRegisterDAO;

import java.util.List;

/**
 * Created by André on 12/12/2015.
 */
public class ReportGenerator {

    private ExcelFileHandle excelFileHandle;
    private DateManager dateManager;

    public ReportGenerator() {
        this.dateManager = DateManager.getDateManager();
        this.excelFileHandle = ExcelFileHandle.getExcelFileHandle();
    }

    public void generateMonthlyReport() {
        List<MemberChargesRegister> memberListOfMonth = getMemberListOfMonth();
        List<EnterpriseChargesRegister> enterpriseListOfMonth = getEnterpriseListOfMonth();
        double totalGain = getTotalGain(memberListOfMonth, enterpriseListOfMonth);

        excelFileHandle.saveActivityLog(memberListOfMonth, enterpriseListOfMonth, totalGain);
    }

    private List<MemberChargesRegister> getMemberListOfMonth() {
        int month = 12;
        String beginningOfMonth = dateManager.getDate(month);
        String finishOfMonth = dateManager.getNextDate(month);

        List<MemberChargesRegister> registersOfTheMonth;
        MemberChargesRegisterDAO memberChargesRegisterDAO = MemberChargesRegisterDAO.getInstance();
        registersOfTheMonth = memberChargesRegisterDAO.getInSpecificDate(beginningOfMonth, finishOfMonth);

        return registersOfTheMonth;
    }

    private List<EnterpriseChargesRegister> getEnterpriseListOfMonth() {
        int month = 12;
        String beginningOfMonth = dateManager.getDate(month);
        String finishOfMonth = dateManager.getNextDate(month);

        List<EnterpriseChargesRegister> registersOfTheMonth;
        EnterpriseChargesRegisterDAO enterpriseChargesRegisterDAO = EnterpriseChargesRegisterDAO.getInstance();
        registersOfTheMonth = enterpriseChargesRegisterDAO.getInSpecificDate(beginningOfMonth, finishOfMonth);

        return registersOfTheMonth;

    }


    private double getTotalGain(
            List<MemberChargesRegister> memberChargesRegisterList,
            List<EnterpriseChargesRegister> enterpriseChargesRegisters
    ) {
        double totalGain = 0;
        for (int i = 0; i < memberChargesRegisterList.size(); i++) {
            totalGain += memberChargesRegisterList.get(i).getTotalCharge();
        }
        for (int i = 0; i < enterpriseChargesRegisters.size(); i++) {
            totalGain += enterpriseChargesRegisters.get(i).getTotalCharge();
        }
        return totalGain;
    }

}
