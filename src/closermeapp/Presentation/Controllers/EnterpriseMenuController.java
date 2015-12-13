package closermeapp.Presentation.Controllers;

import closermeapp.Bussiness.DebtCollector.DebtCollector;
import closermeapp.Bussiness.EnterpriseManager.EmployeeManager;
import closermeapp.Bussiness.EnterpriseManager.EnterpriseManager;
import closermeapp.Bussiness.Entities.Employee;
import closermeapp.Bussiness.Entities.Enterprise;
import closermeapp.Presentation.Util.Notifier;
import closermeapp.Presentation.Util.TableModel;
import closermeapp.Presentation.Views.Enterprise.EnterpriseMenuView;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.String.valueOf;

/**
 * Created by André on 11/12/2015.
 */
public class EnterpriseMenuController extends AbstractViewController {
    private EnterpriseMenuView enterpriseMenuView;
    private EnterpriseManager enterpriseManager;
    private EmployeeManager employeeManager;
    private EmployeeRegistrationController employeeRegistrationController;
    private EnterpriseRegistrationController enterpriseRegistrationController;
    private ChargeController chargeController;
    private DebtCollector debtCollector;
    private HashMap<String, Enterprise> enterpriseHashMap;
    private List<Employee> employeeList;
    private JComboBox enterpriseComboBox;
    private Notifier notifier;
    private TableModel tableModel;

    public EnterpriseMenuController() {
        this.enterpriseMenuView = new EnterpriseMenuView();
        this.enterpriseManager = EnterpriseManager.getEnterpriseManager();
        this.chargeController = new ChargeController();
        this.debtCollector = DebtCollector.getDebtCollector();
        this.employeeManager = EmployeeManager.getEmployeeManager();
        this.enterpriseHashMap = new HashMap<String, Enterprise>();
        this.employeeRegistrationController = new EmployeeRegistrationController(this);
        this.enterpriseRegistrationController = new EnterpriseRegistrationController(this);
        this.enterpriseComboBox = enterpriseMenuView.getEnterpriseComboBox();
        this.notifier = new Notifier();


        initializeView();
    }

    @Override
    public void openWindow() {
        enterpriseMenuView.setVisible(true);
    }

    public HashMap<String, Enterprise> getEnterpriseHashMap() {
        return enterpriseHashMap;
    }

    public void addEnterpriseToListBox(Enterprise enterprise) {
        String key = enterprise.getName();
        enterpriseHashMap.put(key, enterprise);
        getEnterpriseComboBox().addItem(enterprise.getName());
    }

    public void updateWindow() {
        loadEmployeesToTable();
    }

    private void initializeTable() {
        String[] headers = {"", "Name", "Phone", "Role"};
        tableModel = new TableModel(headers);
        JTable employeeTable = enterpriseMenuView.getEmployeeTable();
        employeeTable.setModel(tableModel);
        employeeTable.getTableHeader().setReorderingAllowed(false);

        TableColumnModel columnModel = employeeTable.getColumnModel();
        int firstColumn = 0;
        int sizeColumn = 1;
        columnModel.getColumn(firstColumn).setPreferredWidth(sizeColumn);
    }

    private void loadAvailableEnterprises() {
        ArrayList<Enterprise> enterpriseList = getEnterpriseList();

        for (Enterprise enterprise : enterpriseList) {
            addEnterpriseToListBox(enterprise);
        }
    }

    private ArrayList getEnterpriseList() {
        ArrayList<Enterprise> enterpriseList = enterpriseManager.getEnterpriseList();
        return enterpriseList;
    }


    private JComboBox getEnterpriseComboBox() {
        return enterpriseComboBox;
    }


    private void openEmployeeRegistrationWindow() {
        employeeRegistrationController.setEnterprise(getEnterpriseSelected());
        employeeRegistrationController.openWindow();
    }

    private void openEnterpriseRegistrationWindow() {
        enterpriseRegistrationController.openWindow();
    }


    private Enterprise getEnterpriseSelected() {
        String enterpriseName = (String) getEnterpriseComboBox().getSelectedItem();
        Enterprise enterprise = enterpriseHashMap.get(enterpriseName);
        return enterprise;
    }

    private void loadEmployeesToTable() {
        resetTable();

        if (!isEmptyList()) {
            loadListEmployee();
            for (int listIndex = 0; listIndex < employeeList.size(); listIndex++) {

                Employee employee = employeeList.get(listIndex);
                ArrayList memberDataList = createMemberListData(employee, listIndex);
                addRow(memberDataList);
            }
        }

    }

    private ArrayList<String> createMemberListData(Employee employee, int listIndex) {
        ArrayList<String> memberDataList = new ArrayList();

        int tablePosition = listIndex + 1;
        memberDataList.add(valueOf(tablePosition));
        memberDataList.add(employee.getName());
        memberDataList.add(employee.getPhone());
        memberDataList.add(employee.getRole());
        return memberDataList;
    }

    private void loadListEmployee() {
        Enterprise enterprise = getEnterpriseSelected();
        employeeList = enterprise.getEmployeeList();
    }

    private void addRow(ArrayList list) {
        tableModel.addRow(list);
    }

    private void resetTable() {
        tableModel.resetTable();
    }

    private boolean isEmptyList() {
        Enterprise enterprise = getEnterpriseSelected();
        return enterprise.getEmployeeList().isEmpty();
    }

    private void deleteEnterprise() {

        if (isDeletionConfirmed()) {
            Enterprise enterprise = getEnterpriseSelected();

            enterpriseHashMap.remove(enterprise.getName());
            enterpriseComboBox.removeItem(enterprise.getName());
            enterpriseManager.deleteEnterprise(enterprise);

            String title = "Empresa borrada";
            String message = "Se ha borrado con exito";
            notifier.showSuccessMessage(title, message);
        }

    }

    private void deleteEmployee() {
        JTable employeeTable = enterpriseMenuView.getEmployeeTable();
        int numberRowSelected = employeeTable.getSelectedRow();
        boolean validRow = numberRowSelected >= 0;

        if (validRow && isDeletionConfirmed()) {
            final int columnId = 0;
            String tablePosition = (String) employeeTable.getValueAt(numberRowSelected, columnId);
            int employeeListPosition = Integer.parseInt(tablePosition) - 1;

            Enterprise enterprise = getEnterpriseSelected();
            employeeManager.deleteEmployee(employeeListPosition, enterprise);

            loadEmployeesToTable();

            String title = "Empleado borrado";
            String message = "Se ha borrado con exito";
            notifier.showSuccessMessage(title, message);
        }
    }

    private boolean isDeletionConfirmed() {
        String messageConfirm = "¿Estas seguro que deseas eliminarlo?";
        int optionSelected = notifier.showConfirmDialog(messageConfirm);
        return optionSelected == notifier.getYES_OPTION();
    }

    private void showChargeWindow() {
        Enterprise enterprise = getEnterpriseSelected();
        double totalCost = debtCollector.chargeTheEnterprise(enterprise);
        chargeController.setTotalChargeMessage(totalCost);
        chargeController.openWindow();
    }

    @Override
    protected void initializeView() {
        configureWindow(enterpriseMenuView);
        initializeTable();
        loadAvailableEnterprises();
        setEvents();
        loadEmployeesToTable();
    }

    @Override
    protected void setEvents() {
        enterpriseMenuView.getRegisterEmployeeButton().addActionListener(actionEvent -> openEmployeeRegistrationWindow());
        enterpriseMenuView.getRegisterEnterpriseButton().addActionListener(actionEvent -> openEnterpriseRegistrationWindow());
        enterpriseMenuView.getEnterpriseComboBox().addActionListener(actionEvent -> loadEmployeesToTable());
        enterpriseMenuView.getDeleteEnterpriseButton().addActionListener(actionEvent -> deleteEnterprise());
        enterpriseMenuView.getDeleteEmployeeButton().addActionListener(actionEvent -> deleteEmployee());
        enterpriseMenuView.getChargeButton().addActionListener(actionEvent -> showChargeWindow());
    }

}
