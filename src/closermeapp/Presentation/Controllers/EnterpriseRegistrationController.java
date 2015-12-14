package closermeapp.Presentation.Controllers;

import closermeapp.Bussiness.EnterpriseManager.EnterpriseManager;
import closermeapp.Bussiness.Entities.Enterprise;
import closermeapp.Presentation.Views.Enterprise.EnterpriseRegistrationView;

import javax.swing.*;
import java.util.HashMap;

/**
 * Created by André on 06/12/2015.
 */
public class EnterpriseRegistrationController extends AbstractViewController {
    private EnterpriseRegistrationView enterpriseRegistrationView;
    private EnterpriseMenuController enterpriseMenuController;

    public EnterpriseRegistrationController(EnterpriseMenuController enterpriseMenuController) {
        setEnterpriseRegistrationView( new EnterpriseRegistrationView() );
        setEnterpriseMenuController( enterpriseMenuController );

        initializeView();
    }

    @Override
    public void openWindow() {
        getEnterpriseRegistrationView().setVisible( true );
    }

    @Override
    protected void initializeView() {
        configureWindow( getEnterpriseRegistrationView() );
        setEvents();
    }

    @Override
    protected void setEvents() {
        getEnterpriseRegistrationView().getCancelButton().addActionListener( actionEvent -> closeWindow() );
        getEnterpriseRegistrationView().getAddEnterpriseButton().addActionListener( actionEvent -> registerEnterprise() );
    }

    private void registerEnterprise() {

        String name = getEnterpriseRegistrationView().getNameTextField().getText();
        String address = getEnterpriseRegistrationView().getAddressTextField().getText();
        String city = getEnterpriseRegistrationView().getCityTextField().getText();
        String phone = getEnterpriseRegistrationView().getPhoneTextField().getText();
        String email = getEnterpriseRegistrationView().getEmailTextField().getText();

        JComboBox membershipTypeComboBox = getEnterpriseRegistrationView().getMembershipTypeComboBox();
        String membershipType = (String) membershipTypeComboBox.getSelectedItem();

        boolean isValidField = !isEmptyFields(name, address, city, phone, email);

        String title;
        String message;
        if (isValidField) {

            if (isValidEnterprise(name)) {

                sendEnterpriseDataToManager(name, address, city, phone, email, membershipType);
                title = "Agregado";
                message = "Empresa registrada";
                getNotifier().showSuccessMessage( title, message );

            } else {
                message = "Ya existe esta empresa";
                getNotifier().showWarningMessage( message );
            }

        } else {
            message = "Por favor rellene todos los campos";
            getNotifier().showWarningMessage( message );
        }
    }


    private void sendEnterpriseDataToManager(
            String name,
            String address,
            String city,
            String phone,
            String email,
            String membershipType
    ) {
        EnterpriseManager enterpriseManager = EnterpriseManager.GetInstance();

        Enterprise enterprise = enterpriseManager.createEnterprise(name, address, city, phone, email, membershipType);
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
        getEnterpriseRegistrationView().getNameTextField().setText( whiteSpace );
        getEnterpriseRegistrationView().getAddressTextField().setText( whiteSpace );
        getEnterpriseRegistrationView().getCityTextField().setText( whiteSpace );
        getEnterpriseRegistrationView().getPhoneTextField().setText( whiteSpace );
        getEnterpriseRegistrationView().getEmailTextField().setText( whiteSpace );
    }

    private boolean isValidEnterprise(String enterpriseName) {
        HashMap enterpriseUnavailableList = getEnterpriseMenuController().getEnterpriseHashMap();
        return !enterpriseUnavailableList.containsKey(enterpriseName);
    }

    private void updateWindow(Enterprise enterprise) {
        getEnterpriseMenuController().addEnterpriseToListBox( enterprise );
        resetFields();
    }

    private void closeWindow() {
        getEnterpriseRegistrationView().dispose();
    }


    private void setEnterpriseMenuController(EnterpriseMenuController enterpriseMenuController) {
        this.enterpriseMenuController = enterpriseMenuController;
    }

    private void setEnterpriseRegistrationView(EnterpriseRegistrationView enterpriseRegistrationView) {
        this.enterpriseRegistrationView = enterpriseRegistrationView;
    }

    private EnterpriseMenuController getEnterpriseMenuController() {
        return enterpriseMenuController;
    }

    private EnterpriseRegistrationView getEnterpriseRegistrationView() {
        return enterpriseRegistrationView;
    }


}
