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
    private static final ReportGenerator reportGenerator = new ReportGenerator();
    private ExcelFileHandle excelFileHandle;
    private DateManager dateManager;

    private ReportGenerator() {
        this.dateManager = DateManager.getInstance();
        this.excelFileHandle = ExcelFileHandle.GetInstance();
    }

    public static ReportGenerator GetInstance() {
        return reportGenerator;
    }

    public void generateMonthlyReport(int month) {
        String beginningOfMonth = dateManager.getDate(month);
        String finishOfMonth = dateManager.getNextDate(month);

        List<MemberChargesRegister> memberListOfMonth;
        memberListOfMonth = getMemberListOfMonth(beginningOfMonth, finishOfMonth);

        List<EnterpriseChargesRegister> enterpriseListOfMonth;
        enterpriseListOfMonth = getEnterpriseListOfMonth(beginningOfMonth, finishOfMonth);

        double totalGain = getTotalGain(memberListOfMonth, enterpriseListOfMonth);

        excelFileHandle.saveActivityLog(memberListOfMonth, enterpriseListOfMonth, totalGain, beginningOfMonth);
    }

    public void generateTodayReport() {
        int daysApart = 1;
        String yesterday = dateManager.getPastDate(daysApart);
        String tomorrow = dateManager.getFutureDate(daysApart);

        List<MemberChargesRegister> memberListOfMonth;
        memberListOfMonth = getMemberListOfMonth(yesterday, tomorrow);

        List<EnterpriseChargesRegister> enterpriseListOfMonth;
        enterpriseListOfMonth = getEnterpriseListOfMonth(yesterday, tomorrow);

        double totalGain = getTotalGain(memberListOfMonth, enterpriseListOfMonth);

        String today = dateManager.getTodayDate();
        excelFileHandle.saveActivityLog(memberListOfMonth, enterpriseListOfMonth, totalGain, today);
    }

    public void generateWeeklyReport() {
        int daysApart = 7;
        String yesterday = dateManager.getPastDate(daysApart);
        String today = dateManager.getTodayDate();

        List<MemberChargesRegister> memberListOfMonth;
        memberListOfMonth = getMemberListOfMonth(yesterday, today);

        List<EnterpriseChargesRegister> enterpriseListOfMonth;
        enterpriseListOfMonth = getEnterpriseListOfMonth(yesterday, today);

        double totalGain = getTotalGain(memberListOfMonth, enterpriseListOfMonth);


        excelFileHandle.saveActivityLog(memberListOfMonth, enterpriseListOfMonth, totalGain, today);
    }


    private List<MemberChargesRegister> getMemberListOfMonth(String beginningOfMonth, String finishOfMonth) {

        List<MemberChargesRegister> registersOfTheMonth;
        MemberChargesRegisterDAO memberChargesRegisterDAO = MemberChargesRegisterDAO.GetInstance();
        registersOfTheMonth = memberChargesRegisterDAO.getInSpecificDate(beginningOfMonth, finishOfMonth);

        return registersOfTheMonth;
    }

    private List<EnterpriseChargesRegister> getEnterpriseListOfMonth(String beginningOfMonth, String finishOfMonth) {

        List<EnterpriseChargesRegister> registersOfTheMonth;
        EnterpriseChargesRegisterDAO enterpriseChargesRegisterDAO = EnterpriseChargesRegisterDAO.GetInstance();
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
