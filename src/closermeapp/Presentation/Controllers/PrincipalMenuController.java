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


    public PrincipalMenuController() {
        setPrincipalMenuView( new PrincipalMenuView() );
        setMembersMenuController( new MembersMenuController() );
        setEnterpriseMenuController( new EnterpriseMenuController() );
        setCallLogController( new CallLogController() );
        setReportController( new ReportController() );
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

    }

    public void setCallLogController(CallLogController callLogController) {
        this.callLogController = callLogController;
    }

    public void setEnterpriseMenuController(EnterpriseMenuController enterpriseMenuController) {
        this.enterpriseMenuController = enterpriseMenuController;
    }

    public void setMembersMenuController(MembersMenuController membersMenuController) {
        this.membersMenuController = membersMenuController;
    }

    public void setPrincipalMenuView(PrincipalMenuView principalMenuView) {
        this.principalMenuView = principalMenuView;
    }

    public void setReportController(ReportController reportController) {
        this.reportController = reportController;
    }

    public CallLogController getCallLogController() {
        return callLogController;
    }

    public EnterpriseMenuController getEnterpriseMenuController() {
        return enterpriseMenuController;
    }

    public MembersMenuController getMembersMenuController() {
        return membersMenuController;
    }

    public PrincipalMenuView getPrincipalMenuView() {
        return principalMenuView;
    }

    public ReportController getReportController() {
        return reportController;
    }
}
