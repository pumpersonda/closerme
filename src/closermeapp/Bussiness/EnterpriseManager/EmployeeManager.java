package closermeapp.Bussiness.EnterpriseManager;

/**
 * Created by André on 05/12/2015.
 */
public class EmployeeManager {
    private static EmployeeManager employeeManager;

    private EmployeeManager() {

    }

    public static EmployeeManager getEmployeeManager() {
        if (employeeManager == null) {
            employeeManager = new EmployeeManager();
        }
        return employeeManager;
    }
}
