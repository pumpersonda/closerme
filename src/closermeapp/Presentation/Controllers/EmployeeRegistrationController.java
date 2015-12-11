package closermeapp.Presentation.Controllers;

import closermeapp.Bussiness.EnterpriseManager.EmployeeManager;
import closermeapp.Bussiness.EnterpriseManager.EnterpriseManager;
import closermeapp.Bussiness.Entities.Employee;
import closermeapp.Bussiness.Entities.Enterprise;
import closermeapp.Presentation.Util.Notifier;
import closermeapp.Presentation.Views.Enterprise.EmployeeRegistrationView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by André on 05/12/2015.
 */
public class EmployeeRegistrationController extends AbstractViewController {
    private EmployeeRegistrationView employeeRegistrationView;
    private EnterpriseRegistrationController enterpriseRegistrationController;
    private EnterpriseManager enterpriseManager;
    private EmployeeManager employeeManager;
    private HashMap<String, Enterprise> enterpriseList;
    private final String INVALID_ENTERPRISE = "Seleccione una empresa";
    private Notifier notifier;

    public EmployeeRegistrationController() {
        this.employeeRegistrationView = new EmployeeRegistrationView();
        this.enterpriseRegistrationController = new EnterpriseRegistrationController(this);
        this.enterpriseManager = EnterpriseManager.getEnterpriseManager();
        this.employeeManager = EmployeeManager.getEmployeeManager();
        this.notifier = new Notifier();
        this.enterpriseList = new HashMap<String, Enterprise>();

        initializeView();

    }

    @Override
    public void openWindow() {
        employeeRegistrationView.setVisible(true);
    }

    public void addEnterpriseToListBox(Enterprise enterprise) {
        String key = enterprise.getName();
        enterpriseList.put(key, enterprise);
        JComboBox enterpriseListBox = employeeRegistrationView.getEnterpriseComboBox();
        enterpriseListBox.addItem(enterprise.getName());
    }

    public HashMap<String, Enterprise> getEnterpriseList() {
        return enterpriseList;
    }


    private void registerEmployee() {
        String name = employeeRegistrationView.getEmployeeNameTextBox().getText();
        String phone = employeeRegistrationView.getEmployeeCellPhoneTextBox().getText();
        String role = employeeRegistrationView.getEmployeeRoleTextBox().getText();
        String enterpriseName = (String) employeeRegistrationView.getEnterpriseComboBox().getSelectedItem();

        boolean isValidField = !isEmptyFields(name, phone, role);
        String message;

        if (isValidField) {

            if (isValidEnterprise()) {
                Enterprise enterprise = searchEnterprise(enterpriseName);
                Employee employee = employeeManager.createEmployee(name, phone, role);
                employeeManager.addEmployee(employee, enterprise);

                String title = "Agregado";
                message = "Empleado agregado correctamente";
                notifier.showSuccessMessage(title, message);
            } else {
                message = "Seleccione una Empresa";
                notifier.showWarningMessage(message);
            }
        } else {
            message = "Rellene todos los campos";
            notifier.showWarningMessage(message);
        }

    }

    private boolean isEmptyFields(String name, String phone, String role) {
        return (name.isEmpty() || phone.isEmpty() || role.isEmpty());
    }

    private void openEnterpriseRegister() {
        enterpriseRegistrationController.openWindow();
    }

    private void closerWindow() {
        employeeRegistrationView.dispose();
    }

    private void initializeEnterpriseList() {
        JComboBox enterpriseListBox = employeeRegistrationView.getEnterpriseComboBox();
        enterpriseListBox.addItem(INVALID_ENTERPRISE);
    }

    private Enterprise searchEnterprise(String enterpriseName) {
        Enterprise enterprise = getEnterpriseList().get(enterpriseName);
        return enterprise;
    }

    private void loadAvailableEnterprises() {
        /*Pasar como parametro la lista porque
            habra una ventana con la tabla de los empleados
         */
        ArrayList<Enterprise> enterpriseList = enterpriseManager.getEnterpriseList();

        for (Enterprise enterprise : enterpriseList) {
            addEnterpriseToListBox(enterprise);
        }
    }

    private boolean isValidEnterprise() {
        String enterpriseName = (String) employeeRegistrationView.getEnterpriseComboBox().getSelectedItem();
        return !enterpriseName.equals(INVALID_ENTERPRISE);
    }

    @Override
    protected void initializeView() {
        configureWindow(employeeRegistrationView);
        initializeEnterpriseList();
        loadAvailableEnterprises();
        setEvents();
    }

    @Override
    protected void setEvents() {
        employeeRegistrationView.getCancelButton().addActionListener(actionEvent -> closerWindow());
        employeeRegistrationView.getRegisterEnterpriseButton().addActionListener(actionEvent -> openEnterpriseRegister());
        employeeRegistrationView.getRegisterEmployeeButton().addActionListener(actionEvent -> registerEmployee());

    }


}
