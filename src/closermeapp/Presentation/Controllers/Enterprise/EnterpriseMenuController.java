package closermeapp.Presentation.Controllers.Enterprise;

import closermeapp.Bussiness.Cashier.Cashier;
import closermeapp.Bussiness.EnterpriseManager.EmployeeManager;
import closermeapp.Bussiness.EnterpriseManager.EnterpriseManager;
import closermeapp.Bussiness.Entities.Employee;
import closermeapp.Bussiness.Entities.Enterprise;
import closermeapp.Presentation.Controllers.AbstractViewController;
import closermeapp.Presentation.Controllers.Charge.ChargeController;
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
    private EmployeeRegistrationController employeeRegistrationController;
    private EnterpriseRegistrationController enterpriseRegistrationController;
    private HashMap<String, Enterprise> enterpriseHashMap;
    private List<Employee> employeeList;
    private JComboBox enterpriseComboBox;
    private TableModel tableModel;

    public EnterpriseMenuController() {
        setEnterpriseMenuView( new EnterpriseMenuView() );
        setEnterpriseHashMap( new HashMap<String, Enterprise>() );
        setEmployeeRegistrationController( new EmployeeRegistrationController( this ) );
        setEnterpriseRegistrationController( new EnterpriseRegistrationController( this ) );
        setEnterpriseComboBox( getEnterpriseMenuView().getEnterpriseComboBox() );

        initializeView();
    }

    @Override
    public void openWindow() {
        getEnterpriseMenuView().setVisible( true );
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

    @Override
    protected void initializeView() {
        configureWindow( getEnterpriseMenuView() );
        initializeTable();
        loadAvailableEnterprises();
        setEvents();
        loadEmployeesToTable();
    }

    @Override
    protected void setEvents() {
        getEnterpriseMenuView().getRegisterEmployeeButton().addActionListener( actionEvent -> openEmployeeRegistrationWindow() );
        getEnterpriseMenuView().getRegisterEnterpriseButton().addActionListener( actionEvent -> openEnterpriseRegistrationWindow() );
        getEnterpriseMenuView().getEnterpriseComboBox().addActionListener( actionEvent -> loadEmployeesToTable() );
        getEnterpriseMenuView().getDeleteEnterpriseButton().addActionListener( actionEvent -> deleteEnterprise() );
        getEnterpriseMenuView().getDeleteEmployeeButton().addActionListener( actionEvent -> deleteEmployee() );
        getEnterpriseMenuView().getChargeButton().addActionListener( actionEvent -> showChargeWindow() );
    }

    private void initializeTable() {
        String[] headers = {"", "Name", "Phone", "Role"};
        setTableModel( new TableModel( headers ) );
        JTable employeeTable = getEnterpriseMenuView().getEmployeeTable();
        employeeTable.setModel( getTableModel() );
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
        EnterpriseManager enterpriseManager = EnterpriseManager.GetInstance();
        ArrayList<Enterprise> enterpriseList = enterpriseManager.getEnterpriseList();
        return enterpriseList;
    }


    private JComboBox getEnterpriseComboBox() {
        return enterpriseComboBox;
    }


    private void openEmployeeRegistrationWindow() {
        getEmployeeRegistrationController().setEnterprise( getEnterpriseSelected() );
        getEmployeeRegistrationController().openWindow();
    }

    private void openEnterpriseRegistrationWindow() {
        getEnterpriseRegistrationController().openWindow();
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
            for (int listIndex = 0; listIndex < getEmployeeList().size(); listIndex++) {

                Employee employee = getEmployeeList().get( listIndex );
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
        setEmployeeList( enterprise.getEmployeeList() );
    }

    private void addRow(ArrayList list) {
        getTableModel().addRow( list );
    }

    private void resetTable() {
        getTableModel().resetTable();
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

            EnterpriseManager enterpriseManager = EnterpriseManager.GetInstance();
            enterpriseManager.deleteEnterprise(enterprise);

            String title = "Empresa borrada";
            String message = "Se ha borrado con exito";
            getNotifier().showSuccessMessage( title, message );
        }

    }

    private void deleteEmployee() {
        JTable employeeTable = getEnterpriseMenuView().getEmployeeTable();
        int numberRowSelected = employeeTable.getSelectedRow();
        boolean validRow = numberRowSelected >= 0;

        if (validRow && isDeletionConfirmed()) {
            final int columnId = 0;
            String tablePosition = (String) employeeTable.getValueAt(numberRowSelected, columnId);
            int employeeListPosition = Integer.parseInt(tablePosition) - 1;

            Enterprise enterprise = getEnterpriseSelected();

            EmployeeManager employeeManager = EmployeeManager.GetInstance();
            employeeManager.deleteEmployee(employeeListPosition, enterprise);

            loadEmployeesToTable();

            String title = "Empleado borrado";
            String message = "Se ha borrado con exito";
            getNotifier().showSuccessMessage( title, message );
        }
    }

    private boolean isDeletionConfirmed() {
        String messageConfirm = "¿Estas seguro que deseas eliminarlo?";
        int optionSelected = getNotifier().showConfirmDialog( messageConfirm );
        return optionSelected == getNotifier().getYES_OPTION();
    }

    private void showChargeWindow() {
        Cashier cashier = Cashier.getInstance();

        Enterprise enterprise = getEnterpriseSelected();
        double totalCost = cashier.chargeTheEnterprise(enterprise);

        ChargeController chargeController = new ChargeController();
        chargeController.setTotalChargeMessage(totalCost);
        chargeController.openWindow();
    }

    private void setEnterpriseComboBox(JComboBox enterpriseComboBox) {
        this.enterpriseComboBox = enterpriseComboBox;
    }

    private void setEnterpriseHashMap(HashMap<String, Enterprise> enterpriseHashMap) {
        this.enterpriseHashMap = enterpriseHashMap;
    }

    private EnterpriseMenuView getEnterpriseMenuView() {
        return enterpriseMenuView;
    }

    private void setEnterpriseMenuView(EnterpriseMenuView enterpriseMenuView) {
        this.enterpriseMenuView = enterpriseMenuView;
    }

    private EmployeeRegistrationController getEmployeeRegistrationController() {
        return employeeRegistrationController;
    }

    private void setEmployeeRegistrationController(EmployeeRegistrationController employeeRegistrationController) {
        this.employeeRegistrationController = employeeRegistrationController;
    }

    private EnterpriseRegistrationController getEnterpriseRegistrationController() {
        return enterpriseRegistrationController;
    }

    private void setEnterpriseRegistrationController(EnterpriseRegistrationController enterpriseRegistrationController) {
        this.enterpriseRegistrationController = enterpriseRegistrationController;
    }

    private List<Employee> getEmployeeList() {
        return employeeList;
    }

    private void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    private TableModel getTableModel() {
        return tableModel;
    }

    private void setTableModel(TableModel tableModel) {
        this.tableModel = tableModel;
    }
}
