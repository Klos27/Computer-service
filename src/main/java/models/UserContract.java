package models;

import java.util.Date;

public class UserContract {
    // id_user, first_name, last_name, role, date_start, date_end, salary
    int contractId;
    int userId;
    String firstName;
    String lastName;
    int role;
    Date dateStart;
    Date dateEnd;
    Double salary;

    public UserContract() {
    }

    public UserContract(int contractId, int userId, String firstName, String lastName, int role, Date dateStart, Date dateEnd, Double salary) {
        this.contractId = contractId;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.salary = salary;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
