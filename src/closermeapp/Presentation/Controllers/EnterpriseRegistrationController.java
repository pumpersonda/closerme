package closermeapp.Presentation.Controllers;

import closermeapp.Bussiness.Enterprise.EnterpriseManager;
import closermeapp.Bussiness.Entities.Enterprise;
import closermeapp.Presentation.Util.Notifier;
import closermeapp.Presentation.Views.Enterprise.EnterpriseRegistrationView;

/**
 * Created by André on 06/12/2015.
 */
public class EnterpriseRegistrationController extends AbstractViewController {
    private EnterpriseRegistrationView enterpriseRegistrationView;
    private EnterpriseManager enterpriseManager;
    private Notifier notifier;

    public EnterpriseRegistrationController() {
        enterpriseRegistrationView = new EnterpriseRegistrationView();
        enterpriseManager = EnterpriseManager.getEnterpriseManager();
        notifier = new Notifier();

        configureWindow(enterpriseRegistrationView);
        setEvents();

    }

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

        Enterprise enterprise = enterpriseManager.createEnterprise(name, address, city, phone, email);
        enterpriseManager.addEnterprise(enterprise);

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

    }


    private void updateWindow() {

    }

    private void closeWindow() {
        enterpriseRegistrationView.dispose();
    }

    protected void setEvents() {
        enterpriseRegistrationView.getCancelButton().addActionListener(actionEvent -> closeWindow());
        enterpriseRegistrationView.getAddEnterpriseButton().addActionListener(actionEvent -> registerEnterprise());
    }


}
