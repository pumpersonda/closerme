package closermeapp.Presentation.Controllers;

import closermeapp.Bussiness.Entities.CallLog;
import closermeapp.Bussiness.LogManager.CallLogManager;
import closermeapp.Presentation.Views.CallLog.CallLogDataView;

/**
 * Created by André on 30/11/2015.
 */
public class CallLogDataController {
    private CallLogDataView callLogDataView;
    private CallLogController callLogController;
    private CallLogManager callLogManager;

    public CallLogDataController(CallLogController callLogController) {
        this.callLogDataView = new CallLogDataView();
        this.callLogController = callLogController;
        callLogManager = CallLogManager.getCallLogManager();
        configureWindow();
        setEvents();
    }

    public void opeWindow() {
        callLogDataView.setVisible(true);
    }

    private void registerCall() {
        String memberName = callLogDataView.getMemberNameTextBox().getText();
        String numberPhone = callLogDataView.getNumberTextField().getText();
        String duration = getFormattedDuration();
        CallLog callLog = callLogManager.createCalloLog(memberName, numberPhone, duration);
        callLogManager.addLog(callLog);

        windowUpdate(callLog);

    }

    private String getFormattedDuration() {
        String duration = callLogDataView.getHourField().getText() +
                callLogDataView.getFirstSeparateLabel().getText() +
                callLogDataView.getMinuteField().getText() +
                callLogDataView.getSecondSeparateLabel().getText() +
                callLogDataView.getSecondField().getText();
        return duration;
    }

    private void windowUpdate(CallLog callLog) {
        callLogController.addMemberToTable(callLog);
    }


    private void configureWindow() {
        callLogDataView.setLocationRelativeTo(null);
        callLogDataView.setResizable(false);
        callLogDataView.pack();
        callLogDataView.setLocationRelativeTo(null);
    }


    private void setEvents() {
        callLogDataView.getRegisterButton().addActionListener(actionEvent -> registerCall());

    }
}
