package closermeapp.Presentation.Controllers;

import closermeapp.Bussiness.Reports.ReportGenerator;
import closermeapp.Presentation.Util.Notifier;
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
    private Notifier notifier;


    public ReportController() {
        this.reportView = new ReportView();
        this.monthComboBox = reportView.getDateComboBox();
        this.monthHashMap = new HashMap<String, Integer>();
        this.notifier = new Notifier();

        initializeView();
    }

    private void generateMonthReport() {
        ReportGenerator reportGenerator = new ReportGenerator();
        int numberMonth = getMonthSelected();
        reportGenerator.generateMonthlyReport(numberMonth);

        String title = "Reporte creado";
        String message = "Se ha creado el reporte con exito";
        notifier.showSuccessMessage(title, message);
    }

    @Override
    public void openWindow() {
        reportView.setVisible(true);
    }


    private int getMonthSelected() {
        String month = (String) monthComboBox.getSelectedItem();
        int numberMonth = monthHashMap.get(month);
        return numberMonth;
    }

    private void loadMonths() {
        monthHashMap.put("Enero", 1);
        monthHashMap.put("Febrero", 2);
        monthHashMap.put("Marzo", 3);
        monthHashMap.put("Abril", 4);
        monthHashMap.put("Mayo", 5);
        monthHashMap.put("Junio", 6);
        monthHashMap.put("Julio", 7);
        monthHashMap.put("Agosto", 8);
        monthHashMap.put("Septiembre", 9);
        monthHashMap.put("Octubre", 10);
        monthHashMap.put("Noviembre", 11);
        monthHashMap.put("Diciembre", 12);

        monthComboBox.addItem("Enero");
        monthComboBox.addItem("Febrero");
        monthComboBox.addItem("Marzo");
        monthComboBox.addItem("Abril");
        monthComboBox.addItem("Mayo");
        monthComboBox.addItem("Junio");
        monthComboBox.addItem("Julio");
        monthComboBox.addItem("Agosto");
        monthComboBox.addItem("Septiembre");
        monthComboBox.addItem("Octubre");
        monthComboBox.addItem("Noviembre");
        monthComboBox.addItem("Diciembre");
    }

    @Override
    protected void initializeView() {
        configureWindow(reportView);
        loadMonths();
        setEvents();
    }

    @Override
    protected void setEvents() {
        reportView.getGenerateMonthReportButton().addActionListener(actionEvent -> generateMonthReport());
    }
}
