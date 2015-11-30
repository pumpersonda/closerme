package closermeapp.Presentation.Controllers;

import closermeapp.Presentation.Util.Notifier;
import closermeapp.Presentation.Util.TableModel;
import closermeapp.Presentation.Views.CallLog.CallLogView;

import javax.swing.*;
import javax.swing.table.TableColumnModel;

/**
 * Created by André on 29/11/2015.
 */
public class CallLogController {
    private CallLogView callLogView;
    private TableModel tableModel;
    private Notifier notification;

    public CallLogController() {
        callLogView = new CallLogView();

        initTable();
        callLogView.setLocationRelativeTo(null);
        callLogView.setResizable(false);
        callLogView.pack();
        callLogView.setLocationRelativeTo(null);


    }

    public void openWindow() {
        this.callLogView.setVisible(true);
    }

    private void initTable() {
        String[] headers = {"", "Fecha", "Miembro", "Numero", "Duracion"};
        tableModel = new TableModel(headers);

        JTable registerTable = callLogView.getRegisterTable();
        registerTable.setModel(tableModel);
        registerTable.getTableHeader().setReorderingAllowed(false);

        TableColumnModel columnModel = registerTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(1);
    }

    private void setEvents() {

    }
}
