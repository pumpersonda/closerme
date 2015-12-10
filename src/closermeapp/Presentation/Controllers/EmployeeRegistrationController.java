package closermeapp.Presentation.Controllers;

import closermeapp.Presentation.Util.Notifier;
import closermeapp.Presentation.Views.Enterprise.EmployeeRegistrationView;

/**
 * Created by André on 05/12/2015.
 */
public class EmployeeRegistrationController extends AbstractViewController {
    private EmployeeRegistrationView employeeRegistrationView;
    private EnterpriseRegistrationController enterpriseRegistrationController;

    private Notifier notifier;

    public EmployeeRegistrationController() {

        this.enterpriseRegistrationController = new EnterpriseRegistrationController();
        this.employeeRegistrationView = new EmployeeRegistrationView();

        configureWindow(employeeRegistrationView);
        setEvents();
    }

    public void openWindow() {
        employeeRegistrationView.setVisible(true);
    }

    private void registerEmployee() {
        String name = employeeRegistrationView.getMemberNameTextBox().getText();
        String phone = employeeRegistrationView.getMemberCellPhoneTextBox().getText();
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

    protected void setEvents() {
        employeeRegistrationView.getCancelButton().addActionListener(actionEvent -> closerWindow());
        employeeRegistrationView.getAddEnterpriseButton().addActionListener(actionEvent -> openEnterpriseRegister());

    }
}
