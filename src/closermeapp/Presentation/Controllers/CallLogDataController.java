package closermeapp.Presentation.Controllers;

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
        setEvents();
    }

    public void opeWindow() {
        callLogDataView.setVisible(true);
    }

    private void registerCall() {
        String memberName = callLogDataView.getMemberNameTextBox().getText();
        String numberPhone = callLogDataView.getNumberTextField().getText();
        String duration = getFormattedDuration();
        windowUpdate();
        callLogManager.addLog(memberName, numberPhone, duration);
    }

    private String getFormattedDuration() {
        String duration = callLogDataView.getHourField().getText() +
                callLogDataView.getFirstSeparateLabel().getText() +
                callLogDataView.getMinuteField().getText() +
                callLogDataView.getSecondSeparateLabel().getText() +
                callLogDataView.getSecondField().getText();
        return duration;
    }

    private void windowUpdate() {
        callLogController.addMemberToTable();
    }

    private void setEvents() {
        callLogDataView.getRegisterButton().addActionListener(actionEvent -> registerCall());
    }
}
