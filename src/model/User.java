package model;
import java.io.Serializable;
import java.util.Objects;
public class User implements Serializable {
    private String username;
    private String password;
    private UserType userType;
    private String employeeName;
    private String employeeBirthday;
    private String employeePhone;
    private String employeeEmail;
    private double employeeSalary;

    public  User(String username, String password, UserType userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }
    public User(String username, String password, UserType userType, String employeeName, String employeeBirthday,
            String employeePhone, String employeeEmail, double employeeSalary) {
    this.username = username;
    this.userType = userType;
    this.password = password;
    this.employeeName = employeeName;
    this.employeeBirthday = employeeBirthday;
    this.employeePhone = employeePhone;
    this.employeeEmail = employeeEmail;
    this.employeeSalary = employeeSalary;
}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeBirthday() {
        return employeeBirthday;
    }

    public void setEmployeeBirthday(String employeeBirthday) {
        this.employeeBirthday = employeeBirthday;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public double getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(double employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public static class Administrator extends User implements EmployeeManagement {
        public Administrator(String username, String password) {
            super(username, password, UserType.ADMINISTRATOR);
        }
    }

    public static class Manager extends User {
        public Manager(String username, String password) {
            super(username, password, UserType.MANAGER);
        }
    }

    public static class Librarian extends User {
        public Librarian(String username, String password) {
            super(username, password, UserType.LIBRARIAN);
        }
    }
    @Override
    public String toString() {
        return "Username: " + username +
                "\nPassword: " + password +
                "\nUserType: " + userType +
                "\nEmployee Name: " + employeeName +
                "\nEmployee Birthday: " + employeeBirthday +
                "\nEmployee Phone: " + employeePhone +
                "\nEmployee Email: " + employeeEmail +
                "\nEmployee Salary: " + employeeSalary;
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return Objects.equals(employeeEmail, user.employeeEmail) &&
               Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeEmail, password);
    }
}




