package closermeapp.Presentation.Controllers;

import closermeapp.Bussiness.Reports.ReportGenerator;
import closermeapp.Presentation.Views.Report.ReportView;

/**
 * Created by André on 12/12/2015.
 */
public class ReportController extends AbstractViewController {
    private ReportView reportView;

    public ReportController() {
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
        configureWindow(reportView);
        setEvents();
    }

    @Override
    protected void setEvents() {
        reportView.getGenerateMonthReportButton().addActionListener(actionEvent -> generateReport());
    }
}
