package closermeapp.Data.DAOs;

import closermeapp.Bussiness.Entities.CallLog;

import java.util.ArrayList;

/**
 * Created by André on 29/11/2015.
 */
public class CallLogDAO extends AbstractDAO<CallLog> {
    private static final CallLogDAO callLogDAO = new CallLogDAO();

    private CallLogDAO() {
    }

    public static CallLogDAO GetInstance() {
        return callLogDAO;
    }

    @Override
    public void add(CallLog callLog) {
        saveEntity(callLog);

    }

    @Override
    public void delete(CallLog callLog) {
        deleteEntity(callLog);
    }

    @Override
    public void update(CallLog callLog) {
        updateEntity(callLog);
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
