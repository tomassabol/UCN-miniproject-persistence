package model;

public class Supplier {
    private int id;
    private String name;
    private String address;
    private String country;
    private String phone;
    private String email;

    public Supplier(int id, String name, String address, String country, String phone, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.country = country;
        this.phone = phone;
        this.email = email;
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

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
		return "Supplier [id=" + id + ", name=" + name + ", address=" + address + ", country=" + country + ", phoneno="
				+ phone + ", email=" + email + "]";
	}
}
