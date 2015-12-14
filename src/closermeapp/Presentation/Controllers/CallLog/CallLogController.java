package closermeapp.Presentation.Controllers.CallLog;

import closermeapp.Bussiness.CallLogManager.CallLogManager;
import closermeapp.Bussiness.Entities.CallLog;
import closermeapp.Presentation.Controllers.AbstractViewController;
import closermeapp.Presentation.Util.TableModel;
import closermeapp.Presentation.Views.CallLog.CallLogView;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.util.ArrayList;

import static java.lang.String.valueOf;

/**
 * Created by André on 29/11/2015.
 */
public class CallLogController extends AbstractViewController {
    private CallLogView callLogView;
    private CallLogDataController callLogDataController;
    private JTable registerTable;
    private TableModel tableModel;
    private ArrayList<CallLog> callLogList;


    public CallLogController() {
        setCallLogView( new CallLogView() );
        setCallLogDataController( new CallLogDataController( this ) );
        setRegisterTable( getCallLogView().getRegisterTable() );

        initializeView();
    }

    @Override
    public void openWindow() {
        getCallLogView().setVisible( true );
    }

    public void addMemberToTable(CallLog callLog) {
        getCallLogList().add( callLog );
        int lasElementOfList = getCallLogList().size() - 1;

        ArrayList memberDataList = createMemberListData( callLog, lasElementOfList );
        addRow( memberDataList );
    }

    @Override
    protected void initializeView() {
        configureWindow( getCallLogView() );
        initializeTable();
        loadCallLogList();
        loadCallLogsToTable();
        setEvents();
    }

    @Override
    protected void setEvents() {
        getCallLogView().getNewRegisterButton().addActionListener( actionEvent -> openCallLogDataView() );
        getCallLogView().getDeleteButton().addActionListener( actionEvent -> deleteSelectedCallLog() );
        getCallLogView().getDeleteAllButton().addActionListener( actionEvent -> deleteAllCallLogs() );
    }


    private void addRow(ArrayList list) {
        getTableModel().addRow( list );
    }

    private void openCallLogDataView() {
        getCallLogDataController().openWindow();
    }

    private void initializeTable() {
        String[] headers = {"", "Fecha", "Miembro", "Numero", "Duración"};
        setTableModel( new TableModel( headers ) );


        getRegisterTable().setModel( getTableModel() );
        getRegisterTable().getTableHeader().setReorderingAllowed( false );

        TableColumnModel columnModel = getRegisterTable().getColumnModel();
        int firstColumn = 0;
        int sizeColumn = 1;
        columnModel.getColumn( firstColumn ).setPreferredWidth( sizeColumn );
    }

    private ArrayList<String> createMemberListData(CallLog callLog, int listIndex) {
        ArrayList memberDataList = new ArrayList();

        int tablePosition = listIndex + 1;
        memberDataList.add( valueOf( tablePosition ) );
        memberDataList.add( callLog.getDate() );
        memberDataList.add( callLog.getMemberName() );
        memberDataList.add( callLog.getNumberPhone() );
        memberDataList.add( callLog.getDuration() );

        return memberDataList;
    }

    private void loadCallLogsToTable() {
        resetTable();
        for (int indexList = 0; indexList < getCallLogList().size(); indexList++) {
            CallLog aCallLogList = getCallLogList().get( indexList );
            ArrayList memberDataList = createMemberListData( aCallLogList, indexList );
            addRow( memberDataList );
        }
    }

    private void resetTable() {
        getTableModel().resetTable();
    }

    private void deleteSelectedCallLog() {

        int numberRowSelected = getRegisterTable().getSelectedRow();
        boolean validRow = numberRowSelected >= 0;

        if (validRow && isDeletionConfirmed()) {
            final int columnId = 0;
            String tablePosition = (String) getRegisterTable().getValueAt( numberRowSelected, columnId );
            int callLogListPosition = Integer.parseInt( tablePosition ) - 1;

            CallLog callLog = getCallLogList().get( callLogListPosition );
            deleteCallLog( callLog );
            deleteCallLogList( callLogListPosition );

            String title = "Miembro borrado";
            String message = "Se ha borrado con exito";
            getNotifier().showSuccessMessage( title, message );
            loadCallLogsToTable();
        }
    }

    private void deleteAllCallLogs() {
        if (isDeletionConfirmed()) {
            int listSize = getCallLogList().size() - 1;
            for (int listIndex = listSize; listIndex >= 0; listIndex--) {

                CallLog callLog = getCallLogList().get( listIndex );
                deleteCallLog( callLog );
                deleteCallLogList( listIndex );
            }
            loadCallLogsToTable();
        }

    }

    private boolean isDeletionConfirmed() {
        String messageConfirm = "¿Estas seguro que deseas eliminar?";
        int optionSelected = getNotifier().showConfirmDialog( messageConfirm );
        return optionSelected == getNotifier().getYES_OPTION();
    }

    private void deleteCallLog(CallLog callLog) {
        CallLogManager callLogManager = CallLogManager.GetInstance();
        callLogManager.deleteLog( callLog );
    }

    private void deleteCallLogList(int listIndex) {
        getCallLogList().remove( listIndex );
    }

    private void loadCallLogList() {
        CallLogManager callLogManager = CallLogManager.GetInstance();
        setCallLogList( callLogManager.getMemberList() );
    }

    private void setCallLogView(CallLogView callLogView) {
        this.callLogView = callLogView;
    }

    private void setCallLogDataController(CallLogDataController callLogDataController) {
        this.callLogDataController = callLogDataController;
    }

    private void setTableModel(TableModel tableModel) {
        this.tableModel = tableModel;
    }

    private void setCallLogList(ArrayList<CallLog> callLogList) {
        this.callLogList = callLogList;
    }

    public void setRegisterTable(JTable registerTable) {
        this.registerTable = registerTable;
    }

    private CallLogView getCallLogView() {
        return callLogView;
    }

    private CallLogDataController getCallLogDataController() {
        return callLogDataController;
    }

    private TableModel getTableModel() {
        return tableModel;
    }

    private ArrayList<CallLog> getCallLogList() {
        return callLogList;
    }

    public JTable getRegisterTable() {
        return registerTable;
    }


}
