package closermeapp.Presentation.Controllers;

import closermeapp.Bussiness.Entities.CallLog;
import closermeapp.Bussiness.LogManager.CallLogManager;
import closermeapp.Presentation.Util.Notifier;
import closermeapp.Presentation.Views.CallLog.CallLogDataView;

/**
 * Created by André on 30/11/2015.
 */
public class CallLogDataController extends AbstractViewController {
    private CallLogDataView callLogDataView;
    private CallLogController callLogController;
    private CallLogManager callLogManager;
    private Notifier notifier;

    public CallLogDataController(CallLogController callLogController) {
        this.callLogDataView = new CallLogDataView();
        this.callLogController = callLogController;
        notifier = new Notifier();

        callLogManager = CallLogManager.getCallLogManager();

        configureWindow(callLogDataView);
        setEvents();
    }

    public void openWindow() {
        callLogDataView.setVisible(true);
    }

    private void registerCall() {
        String memberName = callLogDataView.getMemberNameTextBox().getText();
        String numberPhone = callLogDataView.getNumberTextField().getText();
        String duration = getFormattedDuration();


        boolean isValidFields = !isEmptyFields(memberName, numberPhone, duration);
        String title = "Adveretencia";
        String message;

        if (isValidFields) {

            if (isValidHour()) {
                CallLog callLog = callLogManager.createCalloLog(memberName, numberPhone, duration);
                callLogManager.addLog(callLog);
                windowUpdate(callLog);
            } else {
                message = "Introduzca una duración valida";
                notifier.showFailMessage(title, message);
            }

        } else {
            message = "Rellene todos los campos";
            notifier.showFailMessage(title, message);
        }
    }

    private String getFormattedDuration() {
        String duration = callLogDataView.getHourField().getText() +
                callLogDataView.getFirstSeparateLabel().getText() +
                callLogDataView.getMinuteField().getText() +
                callLogDataView.getSecondSeparateLabel().getText() +
                callLogDataView.getSecondField().getText();
        return duration;
    }

    private boolean isValidHour() {
        boolean validHour = false;
        try {
            int hour = Integer.parseInt(callLogDataView.getHourField().getText());
            int minute = Integer.parseInt(callLogDataView.getMinuteField().getText());
            int second = Integer.parseInt(callLogDataView.getSecondField().getText());

            if (hour < 60 && minute < 60 && second < 60) {
                validHour = true;
            }

        } catch (NumberFormatException ignored) {

        }
        return validHour;
    }

    private boolean isEmptyFields(String memberName, String numberPhone, String duration) {
        return (memberName.isEmpty() || numberPhone.isEmpty() || duration.isEmpty());
    }

    private void windowUpdate(CallLog callLog) {
        callLogController.addMemberToTable(callLog);
        resetFields();
    }

    private void resetFields() {
        String whiteSpace = "";
        callLogDataView.getMemberNameTextBox().setText(whiteSpace);
        callLogDataView.getHourField().setText(whiteSpace);
        callLogDataView.getMinuteField().setText(whiteSpace);
        callLogDataView.getSecondField().setText(whiteSpace);
        callLogDataView.getNumberTextField().setText(whiteSpace);

    }

    private void closeWindow() {
        callLogDataView.dispose();
    }

    protected void setEvents() {
        callLogDataView.getRegisterButton().addActionListener(actionEvent -> registerCall());
        callLogDataView.getCancelButton().addActionListener(actionEvent -> closeWindow());
    }
}
