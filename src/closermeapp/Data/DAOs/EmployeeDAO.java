package closermeapp.Data.DAOs;

import closermeapp.Bussiness.Entities.Employee;

import java.util.ArrayList;

/**
 * Created by André on 05/12/2015.
 */
public class EmployeeDAO extends AbstractDAO<Employee> {
    private static EmployeeDAO EmployeeDAO;

    private EmployeeDAO() {

    }

    public static EmployeeDAO getEmployeeDAO() {
        if (EmployeeDAO == null) {
            EmployeeDAO = new EmployeeDAO();
        }
        return EmployeeDAO;
    }

    @Override
    protected void add(Employee employee) {
        String query = "add";
        enquire(query, employee);
    }

    @Override
    protected void delete(Employee employee) {

    }

    @Override
    protected void update(Employee employee) {

    }

    @Override
    protected Object get(int objectId) {

        Employee employee = null;

        try {
            openSession();

            employee = (Employee) session.get(Employee.class, objectId);
        } finally {
            session.close();
        }
        return employee;
    }

    @Override
    protected ArrayList<Employee> getList() {
        ArrayList employeeList = null;

        try {
            openSession();

            employeeList = (ArrayList) session.createQuery("from Employee").list();
        } finally {
            session.close();
        }

        return employeeList;
    }
}
