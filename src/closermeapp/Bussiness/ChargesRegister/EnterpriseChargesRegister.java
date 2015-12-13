package closermeapp.Bussiness.ChargesRegister;

import closermeapp.Bussiness.Entities.Enterprise;

/**
 * Created by André on 12/12/2015.
 */
public class EnterpriseChargesRegister {
    private int idReport;
    private String enterpriseName;
    private String enterpriseAddress;
    private String enterpriseCity;
    private String enterprisePhone;
    private String enterpriseEmail;
    private String membershipType;
    private int employeesNumber;
    private String membershipStarDate;
    private double totalCharge;

    public EnterpriseChargesRegister(Enterprise enterprise, double totalCharge) {
        setIdReport(enterprise.getId());
        setEnterpriseName(enterprise.getName());
        setEnterpriseAddress(enterprise.getAddress());
        setEnterpriseCity(enterprise.getCity());
        setEnterprisePhone(enterprise.getPhone());
        setEnterpriseEmail(enterprise.getEmail());
        setMembershipType(enterprise.getMembership().getMembershipType());
        setEmployeesNumber(enterprise.getEmployeeList().size());
        setMembershipStarDate(enterprise.getMembership().getStartDate());
        setTotalCharge(totalCharge);

    }

    public EnterpriseChargesRegister() {
    }

    public void setEmployeesNumber(int employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public void setEnterpriseAddress(String enterpriseAddress) {
        this.enterpriseAddress = enterpriseAddress;
    }

    public void setEnterpriseCity(String enterpriseCity) {
        this.enterpriseCity = enterpriseCity;
    }

    public void setEnterpriseEmail(String enterpriseEmail) {
        this.enterpriseEmail = enterpriseEmail;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public void setEnterprisePhone(String enterprisePhone) {
        this.enterprisePhone = enterprisePhone;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }

    public void setMembershipStarDate(String membershipStarDate) {
        this.membershipStarDate = membershipStarDate;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public void setTotalCharge(double totalCharge) {
        this.totalCharge = totalCharge;
    }

    public int getEmployeesNumber() {
        return employeesNumber;
    }

    public String getEnterpriseAddress() {
        return enterpriseAddress;
    }

    public String getEnterpriseCity() {
        return enterpriseCity;
    }

    public String getEnterpriseEmail() {
        return enterpriseEmail;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public String getEnterprisePhone() {
        return enterprisePhone;
    }

    public int getIdReport() {
        return idReport;
    }

    public String getMembershipStarDate() {
        return membershipStarDate;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public double getTotalCharge() {
        return totalCharge;
    }

    @Override
    public String toString() {
        return "  Name:  " + getEnterpriseName() +
                ",  Address:  " + getEnterpriseAddress() +
                ",  City:  " + getEnterpriseCity() +
                ",  Phone:  " + getEnterprisePhone() +
                ",  Email:  " + getEnterpriseEmail() +
                ",  Membership Type:  " + getMembershipType() +
                ",  Total Charge:  " + getTotalCharge() +
                ",  Employees Number:  " + getEmployeesNumber();
    }
}
