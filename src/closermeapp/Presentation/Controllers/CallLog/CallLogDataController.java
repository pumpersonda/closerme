package closermeapp.Presentation.Controllers.CallLog;

import closermeapp.Bussiness.CallLogManager.CallLogManager;
import closermeapp.Bussiness.Entities.CallLog;
import closermeapp.Presentation.Controllers.AbstractViewController;
import closermeapp.Presentation.Views.CallLog.CallLogDataView;

/**
 * Created by André on 30/11/2015.
 */
public class CallLogDataController extends AbstractViewController {
    private CallLogDataView callLogDataView;
    private CallLogController callLogController;

    public CallLogDataController(CallLogController callLogController) {
        this.setCallLogDataView( new CallLogDataView() );
        this.setCallLogController( callLogController );

        initializeView();
    }

    @Override
    public void openWindow() {
        getCallLogDataView().setVisible( true );
    }

    @Override
    protected void initializeView() {
        configureWindow( getCallLogDataView() );
        setEvents();
    }

    @Override
    protected void setEvents() {
        getCallLogDataView().getRegisterButton().addActionListener( actionEvent -> registerCall() );
        getCallLogDataView().getCancelButton().addActionListener( actionEvent -> closeWindow() );
    }

    private void registerCall() {
        String memberName = getCallLogDataView().getMemberNameTextBox().getText();
        String numberPhone = getCallLogDataView().getNumberTextField().getText();
        String duration = getFormattedDuration();

        boolean isValidFields = !isEmptyFields(memberName, numberPhone, duration);
        String message;
        if (isValidFields) {

            if (isValidHour()) {
                CallLogManager callLogManager = CallLogManager.GetInstance();
                CallLog callLog = callLogManager.createCallLog( memberName, numberPhone, duration );

                callLogManager.addLog(callLog);
                windowUpdate(callLog);
            } else {
                message = "Introduzca una duración valida";
                getNotifier().showFailMessage( message );
            }

        } else {
            message = "Rellene todos los campos";
            getNotifier().showFailMessage( message );
        }
    }

    private String getFormattedDuration() {
        String duration = getCallLogDataView().getHourField().getText() +
                getCallLogDataView().getFirstSeparateLabel().getText() +
                getCallLogDataView().getMinuteField().getText() +
                getCallLogDataView().getSecondSeparateLabel().getText() +
                getCallLogDataView().getSecondField().getText();
        return duration;
    }

    private boolean isValidHour() {
        boolean validHour = false;
        try {
            int hour = Integer.parseInt( getCallLogDataView().getHourField().getText() );
            int minute = Integer.parseInt( getCallLogDataView().getMinuteField().getText() );
            int second = Integer.parseInt( getCallLogDataView().getSecondField().getText() );

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
        getCallLogController().addMemberToTable( callLog );
        resetFields();
    }

    private void resetFields() {
        String whiteSpace = "";
        getCallLogDataView().getMemberNameTextBox().setText( whiteSpace );
        getCallLogDataView().getHourField().setText( whiteSpace );
        getCallLogDataView().getMinuteField().setText( whiteSpace );
        getCallLogDataView().getSecondField().setText( whiteSpace );
        getCallLogDataView().getNumberTextField().setText( whiteSpace );
    }

    private void closeWindow() {
        getCallLogDataView().dispose();
    }

    private void setCallLogDataView(CallLogDataView callLogDataView) {
        this.callLogDataView = callLogDataView;
    }

    private void setCallLogController(CallLogController callLogController) {
        this.callLogController = callLogController;
    }

    private CallLogDataView getCallLogDataView() {
        return callLogDataView;
    }

    private CallLogController getCallLogController() {
        return callLogController;
    }


}
