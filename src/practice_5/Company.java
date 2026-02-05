package practice_5;

public class Company {
    static String companyName = "FORD"; // название компании
    final int employeeID; // номер сотрудника в штатке
    String employeeName; // имя сотрудника

    Company (int someEmployeeID, String someEmployeeName) {
        this.employeeID = someEmployeeID;
        this.employeeName = someEmployeeName;
    }
    String getEmployeeName() {
        return this.employeeName;
    }
    public void setEmployeeName(String newEmployeeName) {
        this.employeeName = newEmployeeName;
    }
    static void printCompanyName() {
        System.out.println("название компании " + Company.companyName);
    }
    void printCompany() {
        System.out.println("номер сотрудника в штатке: " + this.employeeID + ", имя сотрудника: " + this.employeeName + ", название компании: " + Company.companyName);
    }
}
