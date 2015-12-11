package closermeapp.Bussiness.EnterpriseManager;

import closermeapp.Bussiness.Entities.Enterprise;
import closermeapp.Data.DAOs.EnterpriseDAO;

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
        //Employee employee1 = new Employee("a","b","c");
        //enterprise.addEmployee(employee1);
        //employee1.setEnterprise(enterprise);
        return enterprise;

    }

    public void addEnterprise(Enterprise enterprise) {
        System.out.println(enterprise.getName());
        saveEnterprise(enterprise);
    }

    private void saveEnterprise(Enterprise enterprise) {
        enterpriseDAO.add(enterprise);
    }

}
