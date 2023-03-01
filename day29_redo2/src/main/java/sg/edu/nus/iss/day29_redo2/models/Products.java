package sg.edu.nus.iss.day29_redo2.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Products {

    private String id;

    private String retailerId;

    private String productId;

    @NotNull(message = "Please enter name of product")
    @Size(min = 2, max = 30, message = "Please enter product name between 2 and 30 characters")
    private String name;

    @NotNull(message = "Please enter product unit price")
    private Float unitPrice;

    private Float quantity;

    public Products(){

    }
    public Products(String name, Float unitPrice, Float quantity){
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }
    public Products(String id, String name, Float unitPrice, Float quantity){
        this.id = id;
        this.unitPrice = unitPrice;
        this.name = name;
        this.quantity = quantity;
    }
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getRetailerId() {
        return retailerId;
    }
    public void setRetailerId(String retailerId) {
        this.retailerId = retailerId;
    }
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
    public Float getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }
    public Float getQuantity() {
        return quantity;
    }
    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }
}
