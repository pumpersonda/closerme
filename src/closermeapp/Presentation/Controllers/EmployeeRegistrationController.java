package closermeapp.Presentation.Controllers;

import closermeapp.Bussiness.EnterpriseManager.EmployeeManager;
import closermeapp.Bussiness.Entities.Enterprise;
import closermeapp.Presentation.Util.Notifier;
import closermeapp.Presentation.Views.Enterprise.EmployeeRegistrationView;

import javax.swing.*;

/**
 * Created by André on 05/12/2015.
 */
public class EmployeeRegistrationController extends AbstractViewController {
    private EmployeeRegistrationView employeeRegistrationView;
    private EnterpriseRegistrationController enterpriseRegistrationController;
    private EmployeeManager employeeManager;
    private final String INVALID_ENTERPRISE = "Seleccione una empresa";
    private Notifier notifier;

    public EmployeeRegistrationController() {
        this.employeeRegistrationView = new EmployeeRegistrationView();
        this.enterpriseRegistrationController = new EnterpriseRegistrationController(this);
        this.employeeManager = EmployeeManager.getEmployeeManager();
        this.notifier = new Notifier();

        initializeView();

    }

    @Override
    public void openWindow() {
        employeeRegistrationView.setVisible(true);
    }


    public void addEnterpriseToList(Enterprise enterprise) {
        employeeRegistrationView.getEnterpriseComboBox().addItem(enterprise.getName());
    }

    private void registerEmployee() {
        String name = employeeRegistrationView.getMemberNameTextBox().getText();
        String phone = employeeRegistrationView.getMemberCellPhoneTextBox().getText();

        String enterprise = (String) employeeRegistrationView.getEnterpriseComboBox().getSelectedItem();
        boolean isEmpty = isEmptyFields(name, phone);
        if (isEmpty) {

        }

    }

    private boolean isEmptyFields(String name, String phone) {
        return (name.isEmpty() || phone.isEmpty());
    }

    private void openEnterpriseRegister() {
        enterpriseRegistrationController.openWindow();
    }

    private void closerWindow() {
        employeeRegistrationView.dispose();
    }

    private void initializeEnterpriseList() {
        JComboBox enterpriseList = employeeRegistrationView.getEnterpriseComboBox();
        enterpriseList.addItem(INVALID_ENTERPRISE);
    }

    @Override
    protected void initializeView() {
        configureWindow(employeeRegistrationView);
        initializeEnterpriseList();
        setEvents();
    }

    @Override
    protected void setEvents() {
        employeeRegistrationView.getCancelButton().addActionListener(actionEvent -> closerWindow());
        employeeRegistrationView.getAddEnterpriseButton().addActionListener(actionEvent -> openEnterpriseRegister());

    }


}
