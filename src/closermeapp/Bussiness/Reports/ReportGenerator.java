package closermeapp.Bussiness.Reports;

import closermeapp.Bussiness.ChargesRegister.EnterpriseChargesRegister;
import closermeapp.Bussiness.ChargesRegister.EventChargeRegister;
import closermeapp.Bussiness.ChargesRegister.MemberChargesRegister;
import closermeapp.Bussiness.DateManager.DateManager;
import closermeapp.Data.DAOs.EnterpriseChargesRegisterDAO;
import closermeapp.Data.DAOs.EventChargeRegisterDAO;
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

        List<MemberChargesRegister> memberListOfWeek;
        memberListOfWeek = getMemberListRegister( beginningOfMonth, finishOfMonth );

        List<EnterpriseChargesRegister> enterpriseListOfMonth;
        enterpriseListOfMonth = getEnterpriseListRegister( beginningOfMonth, finishOfMonth );

        List<EventChargeRegister> eventListOfMonth;
        eventListOfMonth = getEventListRegister( beginningOfMonth, finishOfMonth );

        double totalGain = getTotalGain( memberListOfWeek, enterpriseListOfMonth );

        excelFileHandle.saveActivityLog(
                memberListOfWeek,
                enterpriseListOfMonth,
                eventListOfMonth,
                totalGain,
                beginningOfMonth
        );
    }

    public void generateTodayReport() {
        int daysApart = 1;
        String yesterday = dateManager.getPastDate(daysApart);
        String tomorrow = dateManager.getFutureDate(daysApart);

        List<MemberChargesRegister> memberTodayList;
        memberTodayList = getMemberListRegister( yesterday, tomorrow );

        List<EnterpriseChargesRegister> enterpriseTodayList;
        enterpriseTodayList = getEnterpriseListRegister( yesterday, tomorrow );

        List<EventChargeRegister> eventTodayList;
        eventTodayList = getEventListRegister( yesterday, tomorrow );

        double totalGain = getTotalGain( memberTodayList, enterpriseTodayList );

        String today = dateManager.getTodayDate();
        excelFileHandle.saveActivityLog(
                memberTodayList,
                enterpriseTodayList,
                eventTodayList,
                totalGain,
                today );
    }

    public void generateWeeklyReport() {
        int daysApart = 7;
        String yesterday = dateManager.getPastDate(daysApart);
        String today = dateManager.getTodayDate();

        List<MemberChargesRegister> memberLisOfWeek;
        memberLisOfWeek = getMemberListRegister( yesterday, today );

        List<EnterpriseChargesRegister> enterpriseListOfWeek;
        enterpriseListOfWeek = getEnterpriseListRegister( yesterday, today );

        List<EventChargeRegister> eventListOfWeek;
        eventListOfWeek = getEventListRegister( yesterday, today );

        double totalGain = getTotalGain( memberLisOfWeek, enterpriseListOfWeek );
        excelFileHandle.saveActivityLog(
                memberLisOfWeek,
                enterpriseListOfWeek,
                eventListOfWeek,
                totalGain,
                today );
    }


    private List<MemberChargesRegister> getMemberListRegister(String beginningDate, String finishDate) {
        List<MemberChargesRegister> memberListRegister;
        MemberChargesRegisterDAO memberChargesRegisterDAO = MemberChargesRegisterDAO.GetInstance();
        memberListRegister = memberChargesRegisterDAO.getInSpecificDate( beginningDate, finishDate );

        return memberListRegister;
    }


    private List<EnterpriseChargesRegister> getEnterpriseListRegister(String beginningDate, String finishDate) {
        List<EnterpriseChargesRegister> enterpriseListRegister;
        EnterpriseChargesRegisterDAO enterpriseChargesRegisterDAO = EnterpriseChargesRegisterDAO.GetInstance();
        enterpriseListRegister = enterpriseChargesRegisterDAO.getInSpecificDate( beginningDate, finishDate );

        return enterpriseListRegister;

    }

    private List<EventChargeRegister> getEventListRegister(String beginningDate, String finishDate) {
        List<EventChargeRegister> eventChargeRegisters;
        EventChargeRegisterDAO eventChargeRegisterDAO = EventChargeRegisterDAO.GetInstance();
        eventChargeRegisters = eventChargeRegisterDAO.getInSpecifiedDate( beginningDate, finishDate );

        return eventChargeRegisters;
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
