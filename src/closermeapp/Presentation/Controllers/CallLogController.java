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

    private void deleteSelectedCallLog() {
        JTable registerTable = callLogView.getRegisterTable();
        int numberRowSelected = registerTable.getSelectedRow();
        boolean validRow = numberRowSelected >= 0;

        if (validRow && isDeletionConfirmed()) {
            final int columnId = 0;
            String tablePosition = (String) registerTable.getValueAt(numberRowSelected, columnId);
            int callLogListPosition = Integer.parseInt(tablePosition) - 1;

            CallLog callLog = callLogList.get(callLogListPosition);
            deleteCallLog(callLog);
            deleteCallLogList(callLogListPosition);

            String title = "Miembro borrado";
            String message = "Se ha borrado con exito";
            notifier.showSuccessMessage(title, message);
            loadCallLogsToTable();
        }
    }

    private void deleteAllCallLogs() {
        if (isDeletionConfirmed()) {
            int listSize = callLogList.size() - 1;
            for (int listIndex = listSize; listIndex >= 0; listIndex--) {

                CallLog callLog = callLogList.get(listIndex);
                deleteCallLog(callLog);
                deleteCallLogList(listIndex);
            }
            loadCallLogsToTable();
        }

    }

    private boolean isDeletionConfirmed() {
        String messageConfirm = "¿Estas seguro que deseas eliminar a este miembro?";
        int optionSelected = notifier.showConfirmDialog(messageConfirm);
        return optionSelected == notifier.getYES_OPTION();
    }

    private void deleteCallLog(CallLog callLog) {
        callLogManager.deleteLog(callLog);
    }

    private void deleteCallLogList(int listIndex) {
        callLogList.remove(listIndex);
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
        callLogView.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        callLogView.setLocationRelativeTo(null);
        initializeView();
    }

    private void setEvents() {
        callLogView.getNewRegisterButton().addActionListener(actionEvent -> openCallLogDataView());
        callLogView.getDeleteButton().addActionListener(actionEvent -> deleteSelectedCallLog());
        callLogView.getDeleteAllButton().addActionListener(actionEvent -> deleteAllCallLogs());
    }
}
