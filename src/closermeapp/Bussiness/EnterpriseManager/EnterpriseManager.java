package closermeapp.Bussiness.EnterpriseManager;

import closermeapp.Bussiness.Entities.Employee;
import closermeapp.Bussiness.Entities.Enterprise;
import closermeapp.Data.DAOs.EnterpriseDAO;

import java.util.ArrayList;

/**
 * Created by André on 05/12/2015.
 */
public class EnterpriseManager {
    private static EnterpriseManager enterpriseManager;
    private EmployeeManager employeeManager;
    private EnterpriseDAO enterpriseDAO;


    private EnterpriseManager() {
        enterpriseDAO = EnterpriseDAO.getEnterpriseDAO();

    }

    public static EnterpriseManager getEnterpriseManager() {
        if (enterpriseManager == null) {
            enterpriseManager = new EnterpriseManager();
        }
        return enterpriseManager;
    }

    public Enterprise createEnterprise(
            String name,
            String address,
            String city,
            String phone,
            String email
    ) {
        Enterprise enterprise = new Enterprise(name, address, city, phone, email);
        // Employee employee1 = new Employee("a","b","c");
        //employee1.setEnterprise(enterprise);
        //enterprise.addEmployee(employee1);
        return enterprise;

    }

    public void addEnterprise(Enterprise enterprise) {
        saveEnterprise(enterprise);
    }

    public void addEmployeeToEnterprise(Employee employee, Enterprise enterprise) {
        enterprise.addEmployee(employee);
        updateEnterprise(enterprise);
    }

    public void updateEnterprise(Enterprise enterprise) {
        enterpriseDAO.update(enterprise);
    }

    public ArrayList<Enterprise> getEnterpriseList() {
        ArrayList<Enterprise> enterpriseList;
        enterpriseList = enterpriseDAO.getList();

        return enterpriseList;
    }

    private void saveEnterprise(Enterprise enterprise) {
        enterpriseDAO.add(enterprise);
    }

}
