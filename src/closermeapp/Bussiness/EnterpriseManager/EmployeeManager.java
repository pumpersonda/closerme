package closermeapp.Bussiness.EnterpriseManager;

import closermeapp.Bussiness.Entities.Employee;
import closermeapp.Bussiness.Entities.Enterprise;

/**
 * Created by André on 05/12/2015.
 */
public class EmployeeManager {
    private static final EmployeeManager employeeManager = new EmployeeManager();
    private EnterpriseManager enterpriseManager;

    private EmployeeManager() {
        enterpriseManager = EnterpriseManager.GetInstance();
    }

    public static EmployeeManager GetInstance() {
        return employeeManager;
    }

    public Employee createEmployee(String name, String phone, String role) {
        Employee employee = new Employee(name, phone, role);
        return employee;
    }

    public void addEmployee(Employee employee, Enterprise enterprise) {
        employee.setEnterprise(enterprise);
        enterpriseManager.addEmployeeToEnterprise(employee, enterprise);

    }

    public void deleteEmployee(int index, Enterprise enterprise) {
        enterpriseManager.removeEmployeeToEnterprise(index, enterprise);
    }



}
