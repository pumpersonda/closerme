package closermeapp.Presentation.Controllers;

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
    private EmployeeRegistrationController employeeRegistrationController;
    private EnterpriseRegistrationController enterpriseRegistrationController;
    private HashMap<String, Enterprise> enterpriseHashMap;
    private List<Employee> employeeList;
    private JComboBox enterpriseComboBox;
    private Notifier notifier;
    private TableModel tableModel;

    public EnterpriseMenuController() {
        this.enterpriseMenuView = new EnterpriseMenuView();
        this.enterpriseManager = EnterpriseManager.getEnterpriseManager();
        this.enterpriseHashMap = new HashMap<String, Enterprise>();
        this.employeeRegistrationController = new EmployeeRegistrationController(this);
        this.enterpriseRegistrationController = new EnterpriseRegistrationController(this);
        this.enterpriseComboBox = enterpriseMenuView.getEnterpriseComboBox();


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
    }

}