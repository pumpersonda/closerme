package closermeapp.Presentation.Controllers;

import closermeapp.Bussiness.Entities.CallLog;
import closermeapp.Bussiness.LogManager.CallLogManager;
import closermeapp.Presentation.Util.Notifier;
import closermeapp.Presentation.Util.TableModel;
import closermeapp.Presentation.Views.CallLog.CallLogView;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.io.Serializable;
import java.util.ArrayList;

import static java.lang.String.valueOf;

/**
 * Created by André on 29/11/2015.
 */
public class CallLogController implements Serializable {
    private CallLogView callLogView;
    private CallLogDataController callLogDataController;
    private CallLogManager callLogManager;
    private TableModel tableModel;
    private ArrayList<CallLog> callLogList;
    private Notifier notifier;

    public CallLogController() {
        callLogView = new CallLogView();
        callLogDataController = new CallLogDataController(this);
        callLogManager = CallLogManager.getCallLogManager();
        notifier = new Notifier();

        initTable();
        callLogView.setLocationRelativeTo(null);
        callLogView.setResizable(false);
        callLogView.pack();
        callLogView.setLocationRelativeTo(null);

        updateCallLogList();
        loadCallLogsToTable();
        setEvents();

    }

    public void openWindow() {
        this.callLogView.setVisible(true);
    }

    public void addMemberToTable() {
        updateCallLogList();
        int lasElementOfList = callLogList.size() - 1;
        int lastElementId = callLogList.get(lasElementOfList).getRegisterId();

        CallLog callLog = callLogManager.getCallLog(lastElementId);

        ArrayList memberDataList = createMemberListData(callLog, lasElementOfList);
        addRow(memberDataList);
    }

    private void addRow(ArrayList list) {
        tableModel.addRow(list);
    }

    private void openCallLogDataView() {
        callLogDataController.opeWindow();
    }


    private void initTable() {
        String[] headers = {"", "Fecha", "Miembro", "Numero", "Duración"};
        tableModel = new TableModel(headers);

        JTable registerTable = callLogView.getRegisterTable();
        registerTable.setModel(tableModel);
        registerTable.getTableHeader().setReorderingAllowed(false);

        TableColumnModel columnModel = registerTable.getColumnModel();
        int firstColumn = 0;
        int sizeColumn = 1;
        columnModel.getColumn(firstColumn).setPreferredWidth(sizeColumn);
    }

    private ArrayList<String> createMemberListData(CallLog callLog, int index) {
        ArrayList<String> memberDataList = new ArrayList();

        memberDataList.add(valueOf(index));
        memberDataList.add(callLog.getDate());
        memberDataList.add(callLog.getMemberName());
        memberDataList.add(callLog.getNumberPhone());
        memberDataList.add(callLog.getDuration());

        return memberDataList;
    }

    private void loadCallLogsToTable() {
        resetTable();
        for (int indexList = 0; indexList < callLogList.size(); indexList++) {
            CallLog aCallLogList = callLogList.get(indexList);
            ArrayList memberDataList = createMemberListData(aCallLogList, indexList);
            addRow(memberDataList);
        }
    }

    private void resetTable() {
        tableModel.resetTable();
    }

    private void confirmDelete() {
        String messageConfirm = "¿Estas seguro que deseas eliminar a este registro?";
        int confirmDialog = notifier.showConfirmDialog(messageConfirm);

        if (confirmDialog == notifier.getYES_OPTION()) {
            deleteCallLog();

            String title = "Eliminado";
            String message = "Se ha eliminado con exito";
            notifier.showSuccessMessage(title, message);
        }

    }

    private void deleteCallLog() {

        int numberRowSelected = callLogView.getRegisterTable().getSelectedRow();
        int columnId = 0;
        int rowId = Integer.parseInt((String) callLogView.getRegisterTable().getValueAt(numberRowSelected, columnId));

        boolean isValidIndex = rowId >= 0;

        if (isValidIndex) {
            tableModel.deleteRow(numberRowSelected);
            deleteMember(rowId);
        }
        loadCallLogsToTable();
    }

    private void deleteMember(int rowId) {
        callLogManager.deleteLog(callLogList.get(rowId));
        updateCallLogList();
    }

    private void updateCallLogList() {
        callLogList = callLogManager.getMemberList();
    }

    private void setEvents() {
        callLogView.getNewRegisterButton().addActionListener(actionEvent -> openCallLogDataView());
        callLogView.getDeleteButton().addActionListener(actionEvent -> confirmDelete());
    }
}
