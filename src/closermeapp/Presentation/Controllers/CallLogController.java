package closermeapp.Presentation.Controllers;

import closermeapp.Bussiness.Entities.CallLog;
import closermeapp.Bussiness.LogManager.CallLogManager;
import closermeapp.Presentation.Util.Notifier;
import closermeapp.Presentation.Util.TableModel;
import closermeapp.Presentation.Views.CallLog.CallLogView;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.util.ArrayList;

import static java.lang.String.valueOf;

/**
 * Created by André on 29/11/2015.
 */
public class CallLogController {
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
        configureWindow();
    }

    public void openWindow() {
        this.callLogView.setVisible(true);
    }

    public void addMemberToTable(CallLog callLog) {
        callLogList.add(callLog);
        int lasElementOfList = callLogList.size() - 1;

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

    private ArrayList<String> createMemberListData(CallLog callLog, int listIndex) {
        ArrayList<String> memberDataList = new ArrayList();

        int tablePosition = listIndex + 1;
        memberDataList.add(valueOf(tablePosition));
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

    private void confirmDelete(CallLog callLog, int tablePosition) {
        String messageConfirm = "¿Estas seguro que deseas eliminar a este registro?";
        int confirmDialog = notifier.showConfirmDialog(messageConfirm);

        if (confirmDialog == notifier.getYES_OPTION()) {

            deleteCallLog(callLog, tablePosition);

            String title = "Eliminado";
            String message = "Se ha eliminado con exito";
            notifier.showSuccessMessage(title, message);
        }

    }

    private void deleteCallLogToTable() {
        JTable callLogsTable = callLogView.getRegisterTable();

        int numberRowSelected = callLogsTable.getSelectedRow();
        boolean isValidRow = numberRowSelected >= 0;

        if (isValidRow) {
            final int columnId = 0;
            int tablePosition = Integer.parseInt((String) callLogsTable.getValueAt(numberRowSelected, columnId)) - 1;
            CallLog callLog = callLogList.get(tablePosition);
            confirmDelete(callLog, tablePosition);
            loadCallLogsToTable();
        }

    }

    private void deleteCallLog(CallLog callLog, int rowId) {
        callLogManager.deleteLog(callLog);
        callLogList.remove(rowId);
    }

    private void updateCallLogList() {
        callLogList = callLogManager.getMemberList();
    }

    private void initializeView() {
        initTable();
        updateCallLogList();
        loadCallLogsToTable();
        setEvents();
    }

    private void configureWindow() {
        callLogView.setLocationRelativeTo(null);
        callLogView.setResizable(false);
        callLogView.pack();
        callLogView.setLocationRelativeTo(null);
        initializeView();
    }

    private void setEvents() {
        callLogView.getNewRegisterButton().addActionListener(actionEvent -> openCallLogDataView());
        callLogView.getDeleteButton().addActionListener(actionEvent -> deleteCallLogToTable());
    }
}
