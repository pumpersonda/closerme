package closermeapp.Bussiness.EnterpriseManager;

import closermeapp.Bussiness.Entities.Employee;
import closermeapp.Bussiness.Entities.Enterprise;
import closermeapp.Bussiness.MemberManager.MembershipManager;
import closermeapp.Data.DAOs.EnterpriseDAO;

import java.util.ArrayList;

/**
 * Created by André on 05/12/2015.
 */
public class EnterpriseManager {
    private static final EnterpriseManager enterpriseManager = new EnterpriseManager();
    private MembershipManager membershipManager;
    private EnterpriseDAO enterpriseDAO;


    private EnterpriseManager() {
        this.enterpriseDAO = EnterpriseDAO.GetInstance();
        this.membershipManager = MembershipManager.GetInstance();
    }

    public static EnterpriseManager GetInstance() {

        return enterpriseManager;
    }

    public Enterprise createEnterprise(
            String name,
            String address,
            String city,
            String phone,
            String email,
            String membershipNameType
    ) {
        Enterprise enterprise = new Enterprise(name, address, city, phone, email);
        membershipManager.addMembershipToEnterprise(enterprise, membershipNameType);
        return enterprise;

    }

    public void addEnterprise(Enterprise enterprise) {
        saveEnterprise(enterprise);
    }

    public void addEmployeeToEnterprise(Employee employee, Enterprise enterprise) {
        enterprise.addEmployee(employee);
        updateEnterprise(enterprise);
    }

    public void removeEmployeeToEnterprise(int index, Enterprise enterprise) {
        enterprise.removeEmployee(index);
        updateEnterprise(enterprise);
    }

    public void updateEnterprise(Enterprise enterprise) {
        enterpriseDAO.update(enterprise);
    }

    public void deleteEnterprise(Enterprise enterprise) {
        enterpriseDAO.delete(enterprise);
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
