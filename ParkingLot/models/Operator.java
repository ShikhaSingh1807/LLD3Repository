package models;

public class Operator extends BaseModal
{
    private String name;
    private String employeeNumber;
    private String contactNumber;
    private Gate gate;   //from operator id get the gate id and from gate id get parking lot id


    public Operator(String name, String employeeNumber, String contactNumber) {
        this.name = name;
        this.employeeNumber = employeeNumber;
        this.contactNumber = contactNumber;
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
