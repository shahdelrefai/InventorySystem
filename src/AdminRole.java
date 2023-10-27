public class AdminRole {
    private EmployeeUserDatabase database;

    public AdminRole()
    {
        this.database = new EmployeeUserDatabase("Employees.txt");
    }

    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber)
    {
        database.insertRecord(new EmployeeUser(employeeId, name, email, address, phoneNumber));
    }

    public EmployeeUser[] getListOfEmployees()
    {
        return database.returnAllRecords().toArray(new EmployeeUser[0]);
    }

    public void removeEmployee(String key)
    {
        database.deleteRecord(key);
    }

    public void logout()
    {
        database.saveToFile();
    }
}
