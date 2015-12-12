package closermeapp.Presentation.Controllers;

import closermeapp.Bussiness.EnterpriseManager.EmployeeManager;
import closermeapp.Bussiness.Entities.Employee;
import closermeapp.Bussiness.Entities.Enterprise;
import closermeapp.Presentation.Util.Notifier;
import closermeapp.Presentation.Views.Enterprise.EmployeeRegistrationView;

/**
 * Created by André on 05/12/2015.
 */
public class EmployeeRegistrationController extends AbstractViewController {
    private EmployeeRegistrationView employeeRegistrationView;
    private EnterpriseMenuController enterpriseMenuController;
    private EmployeeManager employeeManager;
    private Enterprise enterprise;
    private Notifier notifier;

    public EmployeeRegistrationController(EnterpriseMenuController enterpriseMenuController) {
        this.employeeRegistrationView = new EmployeeRegistrationView();
        this.enterpriseMenuController = enterpriseMenuController;
        this.employeeManager = EmployeeManager.getEmployeeManager();
        this.notifier = new Notifier();

        initializeView();

    }

    @Override
    public void openWindow() {
        employeeRegistrationView.setVisible(true);
    }

    private void registerEmployee() {
        String name = employeeRegistrationView.getEmployeeNameTextBox().getText();
        String phone = employeeRegistrationView.getEmployeeCellPhoneTextBox().getText();
        String role = employeeRegistrationView.getEmployeeRoleTextBox().getText();

        boolean isValidField = !isEmptyFields(name, phone, role);
        String message;

        if (isValidField) {


            sendEmployeeDataToManager(name, phone, role);
            String title = "Agregado";
            message = "Empleado agregado correctamente";
            notifier.showSuccessMessage(title, message);

        } else {
            message = "Rellene todos los campos";
            notifier.showWarningMessage(message);
        }

    }


    private void sendEmployeeDataToManager(String name, String phone, String role) {
        Employee employee = employeeManager.createEmployee(name, phone, role);
        employeeManager.addEmployee(employee, getEnterprise());
        updateWindow();
    }
    private boolean isEmptyFields(String name, String phone, String role) {
        return (name.isEmpty() || phone.isEmpty() || role.isEmpty());
    }

    private void closerWindow() {
        employeeRegistrationView.dispose();
    }

    private void resetFields() {
        String whiteSpace = "";
        employeeRegistrationView.getEmployeeNameTextBox().setText(whiteSpace);
        employeeRegistrationView.getEmployeeCellPhoneTextBox().setText(whiteSpace);
        employeeRegistrationView.getEmployeeRoleTextBox().setText(whiteSpace);
    }

    private void updateWindow() {
        resetFields();
        enterpriseMenuController.updateWindow();
    }

    @Override
    protected void initializeView() {
        configureWindow(employeeRegistrationView);
        setEvents();
    }

    @Override
    protected void setEvents() {
        employeeRegistrationView.getCancelButton().addActionListener(actionEvent -> closerWindow());
        employeeRegistrationView.getRegisterEmployeeButton().addActionListener(actionEvent -> registerEmployee());

    }


    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }
}
