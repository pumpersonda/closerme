package closermeapp.Presentation.Controllers;

import closermeapp.Bussiness.EnterpriseManager.EnterpriseManager;
import closermeapp.Bussiness.Entities.Enterprise;
import closermeapp.Presentation.Util.Notifier;
import closermeapp.Presentation.Views.Enterprise.EnterpriseRegistrationView;

import java.util.HashMap;

/**
 * Created by André on 06/12/2015.
 */
public class EnterpriseRegistrationController extends AbstractViewController {
    private EnterpriseRegistrationView enterpriseRegistrationView;
    private EnterpriseManager enterpriseManager;
    private EmployeeRegistrationController employeeRegistrationController;
    private Notifier notifier;

    public EnterpriseRegistrationController(EmployeeRegistrationController employeeRegistrationController) {
        enterpriseRegistrationView = new EnterpriseRegistrationView();
        enterpriseManager = EnterpriseManager.getEnterpriseManager();
        this.employeeRegistrationController = employeeRegistrationController;
        notifier = new Notifier();

        initializeView();
    }

    @Override
    public void openWindow() {
        enterpriseRegistrationView.setVisible(true);
    }

    private void registerEnterprise() {

        String name = enterpriseRegistrationView.getNameTextField().getText();
        String address = enterpriseRegistrationView.getAddressTextField().getText();
        String city = enterpriseRegistrationView.getCityTextField().getText();
        String phone = enterpriseRegistrationView.getPhoneTextField().getText();
        String email = enterpriseRegistrationView.getEmailTextField().getText();
        boolean isValidField = !isEmptyFields(name, address, city, phone, email);

        if (isValidField) {

            if (isValidEnterprise(name)) {

                sendEnterpriseDataToManager(name, address, city, phone, email);
                notifier.showSuccessMessage("Agregado", "Empresa registrada");

            } else {
                notifier.showWarningMessage("Advertencia", "Ya existe esta empresa");
            }

        } else {
            notifier.showWarningMessage("Advertencia", "Por favor rellene todos los campos");
        }
    }


    private void sendEnterpriseDataToManager(
            String name,
            String address,
            String city,
            String phone,
            String email
    ) {
        Enterprise enterprise = enterpriseManager.createEnterprise(name, address, city, phone, email);
        enterpriseManager.addEnterprise(enterprise);
        updateWindow(enterprise);
    }

    private boolean isEmptyFields(
            String name,
            String address,
            String city,
            String phone,
            String email
    ) {
        return (name.isEmpty() || address.isEmpty() || city.isEmpty() || phone.isEmpty() || email.isEmpty());
    }

    private void resetFields() {
        String whiteSpace = "";
        enterpriseRegistrationView.getNameTextField().setText(whiteSpace);
        enterpriseRegistrationView.getAddressTextField().setText(whiteSpace);
        enterpriseRegistrationView.getCityTextField().setText(whiteSpace);
        enterpriseRegistrationView.getPhoneTextField().setText(whiteSpace);
        enterpriseRegistrationView.getEmailTextField().setText(whiteSpace);
    }

    private boolean isValidEnterprise(String enterpriseName) {
        HashMap enterpriseUnavailableList = employeeRegistrationController.getEnterpriseList();
        return !enterpriseUnavailableList.containsKey(enterpriseName);
    }

    private void updateWindow(Enterprise enterprise) {
        employeeRegistrationController.addEnterpriseToListBox(enterprise);
        resetFields();

    }

    private void closeWindow() {
        enterpriseRegistrationView.dispose();
    }

    @Override
    protected void initializeView() {
        configureWindow(enterpriseRegistrationView);
        setEvents();
    }


    protected void setEvents() {
        enterpriseRegistrationView.getCancelButton().addActionListener(actionEvent -> closeWindow());
        enterpriseRegistrationView.getAddEnterpriseButton().addActionListener(actionEvent -> registerEnterprise());
    }


}
