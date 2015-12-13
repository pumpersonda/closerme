package closermeapp.Presentation.Controllers;

import closermeapp.Bussiness.Reports.ReportGenerator;
import closermeapp.Presentation.Views.ReportView;

/**
 * Created by André on 12/12/2015.
 */
public class PrincipalMenuController extends AbstractViewController {
    private ReportView reportView;

    public PrincipalMenuController() {
        this.reportView = new ReportView();
        initializeView();
    }

    private void generateReport() {
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.generateMonthlyReport();
    }

    @Override
    public void openWindow() {
        reportView.setVisible(true);
    }

    @Override
    protected void initializeView() {
        setEvents();
    }

    @Override
    protected void setEvents() {
        reportView.getGenerateReportButton().addActionListener(actionEvent -> generateReport());
    }
}
