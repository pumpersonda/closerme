package closermeapp.Presentation.Controllers.Enterprise;

import closermeapp.Bussiness.EnterpriseManager.EmployeeManager;
import closermeapp.Bussiness.Entities.Employee;
import closermeapp.Bussiness.Entities.Enterprise;
import closermeapp.Presentation.Controllers.AbstractViewController;
import closermeapp.Presentation.Views.Enterprise.EmployeeRegistrationView;

/**
 * Created by André on 05/12/2015.
 */
public class EmployeeRegistrationController extends AbstractViewController {
    private EmployeeRegistrationView employeeRegistrationView;
    private EnterpriseMenuController enterpriseMenuController;
    private Enterprise enterprise;

    public EmployeeRegistrationController(EnterpriseMenuController enterpriseMenuController) {
        setEmployeeRegistrationView( new EmployeeRegistrationView() );
        setEnterpriseMenuController( enterpriseMenuController );

        initializeView();

    }

    @Override
    public void openWindow() {
        getEmployeeRegistrationView().setVisible( true );
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    @Override
    protected void initializeView() {
        configureWindow( getEmployeeRegistrationView() );
        setEvents();
    }

    @Override
    protected void setEvents() {
        getEmployeeRegistrationView().getCancelButton().addActionListener( actionEvent -> closerWindow() );
        getEmployeeRegistrationView().getRegisterEmployeeButton().addActionListener( actionEvent -> registerEmployee() );

    }

    private Enterprise getEnterprise() {
        return enterprise;
    }

    private void setEmployeeRegistrationView(EmployeeRegistrationView employeeRegistrationView) {
        this.employeeRegistrationView = employeeRegistrationView;
    }

    private void setEnterpriseMenuController(EnterpriseMenuController enterpriseMenuController) {
        this.enterpriseMenuController = enterpriseMenuController;
    }

    private EmployeeRegistrationView getEmployeeRegistrationView() {
        return employeeRegistrationView;
    }

    private EnterpriseMenuController getEnterpriseMenuController() {
        return enterpriseMenuController;
    }

    private void registerEmployee() {
        String name = getEmployeeRegistrationView().getEmployeeNameTextBox().getText();
        String phone = getEmployeeRegistrationView().getEmployeeCellPhoneTextBox().getText();
        String role = getEmployeeRegistrationView().getEmployeeRoleTextBox().getText();

        boolean isValidField = !isEmptyFields(name, phone, role);
        String message;

        if (isValidField) {
            sendEmployeeDataToManager(name, phone, role);

            String title = "Agregado";
            message = "Empleado agregado correctamente";
            getNotifier().showSuccessMessage( title, message );

        } else {
            message = "Rellene todos los campos";
            getNotifier().showWarningMessage( message );
        }

    }

    private void sendEmployeeDataToManager(String name, String phone, String role) {
        EmployeeManager employeeManager = EmployeeManager.GetInstance();
        Employee employee = employeeManager.createEmployee(name, phone, role);

        employeeManager.addEmployee(employee, getEnterprise());
        updateWindow();
    }

    private boolean isEmptyFields(String name, String phone, String role) {
        return (name.isEmpty() || phone.isEmpty() || role.isEmpty());
    }

    private void closerWindow() {
        getEmployeeRegistrationView().dispose();
    }

    private void resetFields() {
        String whiteSpace = "";
        getEmployeeRegistrationView().getEmployeeNameTextBox().setText( whiteSpace );
        getEmployeeRegistrationView().getEmployeeCellPhoneTextBox().setText( whiteSpace );
        getEmployeeRegistrationView().getEmployeeRoleTextBox().setText( whiteSpace );
    }

    private void updateWindow() {
        resetFields();
        getEnterpriseMenuController().updateWindow();
    }

}
