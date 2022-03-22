package model;

public class Customer {
    
    /**
     * Fields for class Customer
     */
    private int id;
    private String name;
    private String address;
    private String city;
    private String phoneNumber;
    private String email;
    //private CustomerType customerType;

    /**
     * Constructor for class Customer
     */
    public Customer(int id, String name, String address, String city, String phoneNumber, String email) { // don't do CustomerType customerType for now
        this.name = name;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
        //this.customerType = customerType;
    }
    
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*
    public CustomerType getCustomerType() {
        return this.customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }
    */

    public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", address=" + address + ", city=" + city + ", city=" + city + ", phone number=" + phoneNumber + ", email=" + email + "]";
	}
    
}
