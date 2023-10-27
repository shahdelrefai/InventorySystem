public class EmployeeUser {
    private String employeeId, name, email, address, phoneNumber;

    public EmployeeUser(String employeeId, String name, String email, String address, String phoneNumber)
    {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String lineRepresentation()
    {
        return this.employeeId + "," + this.name + "," + this.email + "," + this.address + "," + this.phoneNumber;
    }

    public String getSearchKey()
    {
        return this.employeeId;
    }
}
