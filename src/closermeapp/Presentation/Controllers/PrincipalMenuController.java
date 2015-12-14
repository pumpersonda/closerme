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
        this.principalMenuView = new PrincipalMenuView();
        this.membersMenuController = new MembersMenuController();
        this.enterpriseMenuController = new EnterpriseMenuController();
        this.callLogController = new CallLogController();
        this.reportController = new ReportController();
        initializeView();
    }

    private void openMembersMenu() {
        membersMenuController.openWindow();
    }

    private void openEnterpriseMenu() {
        enterpriseMenuController.openWindow();
    }

    private void openCallLogMenu() {
        callLogController.openWindow();
    }

    private void openReportMenu() {
        reportController.openWindow();
    }

    @Override
    public void openWindow() {
        principalMenuView.setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureWindow(principalMenuView);
        principalMenuView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setEvents();
    }

    @Override
    protected void setEvents() {
        principalMenuView.getMemberButton().addActionListener(actionEvent -> openMembersMenu());
        principalMenuView.getEnterpriseButton().addActionListener(actionEvent -> openEnterpriseMenu());
        principalMenuView.getCallLogButton().addActionListener(actionEvent -> openCallLogMenu());
        principalMenuView.getReportButton().addActionListener(actionEvent -> openReportMenu());

    }
}
