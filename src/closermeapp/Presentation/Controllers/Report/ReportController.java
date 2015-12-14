package closermeapp.Presentation.Controllers.Report;

import closermeapp.Bussiness.Reports.ReportGenerator;
import closermeapp.Presentation.Controllers.AbstractViewController;
import closermeapp.Presentation.Views.Report.ReportView;

import javax.swing.*;
import java.util.HashMap;

/**
 * Created by André on 12/12/2015.
 */
public class ReportController extends AbstractViewController {
    private ReportView reportView;
    private JComboBox monthComboBox;
    private HashMap<String, Integer> monthHashMap;

    public ReportController() {
        this.setReportView( new ReportView() );
        this.setMonthComboBox( getReportView().getDateComboBox() );
        this.setMonthHashMap( new HashMap<String, Integer>() );
        initializeView();
    }

    @Override
    public void openWindow() {
        getReportView().setVisible( true );
    }

    @Override
    protected void initializeView() {
        configureWindow( getReportView() );
        loadMonths();
        setEvents();
    }

    @Override
    protected void setEvents() {
        getReportView().getGenerateMonthReportButton().addActionListener( actionEvent -> generateMonthReport() );
        getReportView().getGenerateTodaysReport().addActionListener( actionEvent -> generateTodayReport() );
        getReportView().getGenerateWeeklyReportButton().addActionListener( actionEvent -> generateWeeklyReport() );
    }

    private void generateMonthReport() {

        int numberMonth = getMonthSelected();
        ReportGenerator reportGenerator = ReportGenerator.GetInstance();
        reportGenerator.generateMonthlyReport(numberMonth);

        showSuccessMessage();
    }

    private void generateTodayReport() {
        ReportGenerator reportGenerator = ReportGenerator.GetInstance();
        reportGenerator.generateTodayReport();
        showSuccessMessage();
    }

    private void generateWeeklyReport() {
        ReportGenerator reportGenerator = ReportGenerator.GetInstance();
        reportGenerator.generateWeeklyReport();
        showSuccessMessage();
    }

    private void showSuccessMessage() {
        String title = "Reporte creado";
        String message = "Se ha creado el reporte con exito";
        getNotifier().showSuccessMessage( title, message );
    }

    private int getMonthSelected() {
        String month = (String) getMonthComboBox().getSelectedItem();
        int numberMonth = getMonthHashMap().get( month );
        return numberMonth;
    }

    private void loadMonths() {
        getMonthHashMap().put( "Enero", 1 );
        getMonthHashMap().put( "Febrero", 2 );
        getMonthHashMap().put( "Marzo", 3 );
        getMonthHashMap().put( "Abril", 4 );
        getMonthHashMap().put( "Mayo", 5 );
        getMonthHashMap().put( "Junio", 6 );
        getMonthHashMap().put( "Julio", 7 );
        getMonthHashMap().put( "Agosto", 8 );
        getMonthHashMap().put( "Septiembre", 9 );
        getMonthHashMap().put( "Octubre", 10 );
        getMonthHashMap().put( "Noviembre", 11 );
        getMonthHashMap().put( "Diciembre", 12 );

        getMonthComboBox().addItem( "Enero" );
        getMonthComboBox().addItem( "Febrero" );
        getMonthComboBox().addItem( "Marzo" );
        getMonthComboBox().addItem( "Abril" );
        getMonthComboBox().addItem( "Mayo" );
        getMonthComboBox().addItem( "Junio" );
        getMonthComboBox().addItem( "Julio" );
        getMonthComboBox().addItem( "Agosto" );
        getMonthComboBox().addItem( "Septiembre" );
        getMonthComboBox().addItem( "Octubre" );
        getMonthComboBox().addItem( "Noviembre" );
        getMonthComboBox().addItem( "Diciembre" );
    }

    private void setMonthComboBox(JComboBox monthComboBox) {
        this.monthComboBox = monthComboBox;
    }

    private void setMonthHashMap(HashMap<String, Integer> monthHashMap) {
        this.monthHashMap = monthHashMap;
    }

    private void setReportView(ReportView reportView) {
        this.reportView = reportView;
    }

    private JComboBox getMonthComboBox() {
        return monthComboBox;
    }

    private HashMap<String, Integer> getMonthHashMap() {
        return monthHashMap;
    }

    private ReportView getReportView() {
        return reportView;
    }
}
