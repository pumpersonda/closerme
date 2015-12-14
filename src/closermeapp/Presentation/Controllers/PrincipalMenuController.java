package closermeapp.Presentation.Controllers;

import closermeapp.Presentation.Views.PrincipalMenuView;

import javax.swing.*;

/**
 * Created by André on 13/12/2015.
 */
public class PrincipalMenuController extends AbstractViewController {
    private PrincipalMenuView principalMenuView;
    private MembersMenuController membersMenuController;
    private EnterpriseMenuController enterpriseMenuController;
    private CallLogController callLogController;
    private ReportController reportController;
    private EventMenuController eventViewerController;


    public PrincipalMenuController() {
        setPrincipalMenuView( new PrincipalMenuView() );
        setMembersMenuController( new MembersMenuController() );
        setEnterpriseMenuController( new EnterpriseMenuController() );
        setCallLogController( new CallLogController() );
        setReportController( new ReportController() );
        setEventViewerController( new EventMenuController() );

        initializeView();
    }

    private void openMembersMenu() {
        getMembersMenuController().openWindow();
    }

    private void openEnterpriseMenu() {
        getEnterpriseMenuController().openWindow();
    }

    private void openCallLogMenu() {
        getCallLogController().openWindow();
    }

    private void openReportMenu() {
        getReportController().openWindow();
    }

    private void openEventMenu() {
        getEventViewerController().openWindow();
    }

    @Override
    public void openWindow() {
        getPrincipalMenuView().setVisible( true );
    }

    @Override
    protected void initializeView() {
        configureWindow( getPrincipalMenuView() );
        getPrincipalMenuView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        getPrincipalMenuView().getMemberButton().addActionListener( actionEvent -> openMembersMenu() );
        getPrincipalMenuView().getEnterpriseButton().addActionListener( actionEvent -> openEnterpriseMenu() );
        getPrincipalMenuView().getCallLogButton().addActionListener( actionEvent -> openCallLogMenu() );
        getPrincipalMenuView().getReportButton().addActionListener( actionEvent -> openReportMenu() );
        getPrincipalMenuView().getEventButton().addActionListener( actionEvent -> openEventMenu() );

    }

    private void setCallLogController(CallLogController callLogController) {
        this.callLogController = callLogController;
    }

    private void setEnterpriseMenuController(EnterpriseMenuController enterpriseMenuController) {
        this.enterpriseMenuController = enterpriseMenuController;
    }

    private void setMembersMenuController(MembersMenuController membersMenuController) {
        this.membersMenuController = membersMenuController;
    }

    private void setPrincipalMenuView(PrincipalMenuView principalMenuView) {
        this.principalMenuView = principalMenuView;
    }

    private void setReportController(ReportController reportController) {
        this.reportController = reportController;
    }

    public void setEventViewerController(EventMenuController eventViewerController) {
        this.eventViewerController = eventViewerController;
    }

    private CallLogController getCallLogController() {
        return callLogController;
    }

    private EnterpriseMenuController getEnterpriseMenuController() {
        return enterpriseMenuController;
    }

    private MembersMenuController getMembersMenuController() {
        return membersMenuController;
    }

    private PrincipalMenuView getPrincipalMenuView() {
        return principalMenuView;
    }

    private ReportController getReportController() {
        return reportController;
    }

    public EventMenuController getEventViewerController() {
        return eventViewerController;
    }
}
