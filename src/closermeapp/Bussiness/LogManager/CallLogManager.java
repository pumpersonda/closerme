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


    private CallLogManager() {
        callLogDAO = CallLogDAO.getCallLogDAO();
    }

    public static CallLogManager getCallLogManager() {
        if (callLogManager == null) {
            callLogManager = new CallLogManager();
        }
        return callLogManager;
    }

    public CallLog createCalloLog(String memberName, String numberPhone, String duration) {
        CallLog callLog = new CallLog(memberName, numberPhone, duration);
        return callLog;
    }

    public void addLog(CallLog callLog) {
        setDate(callLog);
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

    private void setDate(CallLog callLog) {
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
