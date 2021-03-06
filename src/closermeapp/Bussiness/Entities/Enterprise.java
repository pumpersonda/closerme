package closermeapp.Bussiness.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by André on 05/12/2015.
 */
public class Enterprise implements Serializable {
    private int id;
    private String name;
    private String address;
    private String city;
    private String phone;
    private String email;
    private Membership membership;
    private List<Employee> employeeList = new ArrayList<>();

    public Enterprise(
            String name,
            String address,
            String city,
            String phone,
            String email
    ) {
        this.address = address;
        this.city = city;
        this.email = email;
        this.name = name;
        this.phone = phone;
    }

    public Enterprise() {
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getId() {
        return id;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public Membership getMembership() {
        return membership;
    }

    public void addEmployee(Employee employee) {
        this.employeeList.add(employee);
    }

    public void removeEmployee(int index) {

        this.employeeList.remove(index);
    }
}
