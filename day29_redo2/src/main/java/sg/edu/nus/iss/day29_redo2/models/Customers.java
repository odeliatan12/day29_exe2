package sg.edu.nus.iss.day29_redo2.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Customers {
    
    private String id;

    @NotEmpty(message = "Please enter a name")
    @Size(min = 2, max = 30, message = "Enter a name between 2 and 30 characters")
    private String name;

    @Email
    @Size(min = 2, max = 30, message = "Enter a email address between 2 and 30 characters")
    private String emailAddress;

    @NotEmpty(message = "Please enter a phone number")
    @Size(min = 8, max = 8, message = "Please enter an 8 digit phone number")
    private String phoneNumber;

    @NotEmpty(message = "Please enter a home address")
    private String homeAddress;
    private CustomerOrders cp;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getHomeAddress() {
        return homeAddress;
    }
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }
    public CustomerOrders getCp() {
        return cp;
    }
    public void setCp(CustomerOrders cp) {
        this.cp = cp;
    }
    
    
}
