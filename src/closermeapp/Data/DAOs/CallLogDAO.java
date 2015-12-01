package closermeapp.Data.DAOs;

import closermeapp.Bussiness.Entities.CallLog;

import java.util.ArrayList;

/**
 * Created by André on 29/11/2015.
 */
public class CallLogDAO extends AbstractDAO<CallLog> {
    private static CallLogDAO callLogDAO;

    private CallLogDAO() {
    }

    public static CallLogDAO getCallLogDAO() {
        if (callLogDAO == null) {
            callLogDAO = new CallLogDAO();
        }
        return callLogDAO;
    }

    @Override
    public void add(CallLog callLog) {
        String query = "add";
        enquire(query, callLog);
    }

    @Override
    public void delete(CallLog callLog) {
        String query = "delete";
        enquire(query, callLog);
    }

    @Override
    public void update(CallLog callLog) {
        String query = "update";
        enquire(query, callLog);
    }

    @Override
    public Object get(int objectId) {
        CallLog callLog = null;

        try {
            openSession();
            callLog = (CallLog) session.get(CallLog.class, objectId);
        } finally {
            session.close();
        }
        return callLog;
    }

    @Override
    public ArrayList<CallLog> getList() {
        ArrayList callRegisterList = null;

        try {
            openSession();

            callRegisterList = (ArrayList) session.createQuery("FROM CallLog ").list();
        } finally {
            session.close();
        }

        return callRegisterList;
    }
}
