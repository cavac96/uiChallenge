package models;

public class Billing {
    String name;
    String lastName;
    String address;
    String apto;
    String zip;
    String phone;
    String email;

    public Billing(String name, String lastName, String address, String apto, String zip, String phone, String email) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.apto = apto;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getApto() {
        return apto;
    }

    public String getZip() {
        return zip;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
