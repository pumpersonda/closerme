package closermeapp.Bussiness.LogManager;

import closermeapp.Bussiness.Entities.CallLog;
import closermeapp.Data.DAOs.CallLogDAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Created by André on 30/11/2015.
 */
public class CallLogManager {
    private static CallLogManager callLogManager;
    private CallLogDAO callLogDAO;
    private CallLog callLog;

    private CallLogManager() {
        callLogDAO = CallLogDAO.getCallLogDAO();
    }

    public static CallLogManager getCallLogManager() {
        if (callLogManager == null) {
            callLogManager = new CallLogManager();
        }
        return callLogManager;
    }

    public void addLog(String memberName, String numberPhone, String duration) {
        callLog = new CallLog(memberName, numberPhone, duration);
        setDateCallLog();
        saveLog(callLog);
    }

    public void deleteLog(CallLog callLog) {
        callLogDAO.delete(callLog);
    }

    public CallLog getCallLog(int memberId) {
        CallLog callLog = (CallLog) callLogDAO.get(memberId);
        return callLog;
    }

    public ArrayList getMemberList() {
        ArrayList<CallLog> callLogList;
        callLogList = this.callLogDAO.getList();

        return callLogList;
    }

    private void setDateCallLog() {
        String today = getTodayDate();
        callLog.setDate(today);
    }

    private String getTodayDate() {
        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        return today;
    }

    private void saveLog(CallLog callLog) {
        callLogDAO.add(callLog);
    }


}
