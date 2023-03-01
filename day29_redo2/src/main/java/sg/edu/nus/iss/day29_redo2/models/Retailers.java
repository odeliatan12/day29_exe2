package sg.edu.nus.iss.day29_redo2.models;

import java.util.LinkedList;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Retailers {

    private String id;

    @NotNull(message = "Please enter a valid name")
    @Size(min = 2, max = 30, message = "Please enter name between 2 and 30 characters")
    private String retailerName;

    @NotNull(message = "Phone number cannot be empty")
    @Size(min = 8, max = 8, message = "Please enter an 8 digit phone number")
    private String phoneNumber;

    @Email
    private String emailAddress;

    @NotNull(message = "Address cannot be empty")
    private String address;

    private List<Products> products = new LinkedList<>();
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getretailerName() {
        return retailerName;
    }
    public void setretailerName(String retailerName) {
        this.retailerName = retailerName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public List<Products> getProducts() {
        return products;
    }
    public void setProducts(List<Products> products) {
        this.products = products;
    }
    
    
}
