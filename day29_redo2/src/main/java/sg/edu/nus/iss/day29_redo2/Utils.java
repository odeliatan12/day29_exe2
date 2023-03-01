package sg.edu.nus.iss.day29_redo2;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.day29_redo2.models.CustomerProducts;
import sg.edu.nus.iss.day29_redo2.models.Customers;
import sg.edu.nus.iss.day29_redo2.models.Products;
import sg.edu.nus.iss.day29_redo2.models.Retailers;

public class Utils {

    public static Document toInsertRetailer(Retailers retailer,
        String id){
        Document doc = new Document();
        doc.put("id", id);
        doc.put("name", retailer.getretailerName());
        doc.put("phoneNumber", retailer.getPhoneNumber());
        doc.put("emailAddress", retailer.getEmailAddress());
        return doc;
    }
    
    public static List<Document> toInsertProduct(List<Products> products,
        String id, String productId){
        List<Document> doc = new LinkedList<>();
        for(Products p: products){
            Document docList = new Document("retailerId", id)
                                    .append("productId", productId)
                                    .append("productName", p.getName())
                                    .append("unitPrice", p.getUnitPrice())
                                    .append("quantity", p.getQuantity());
            doc.add(docList);
        }
        return doc;
    }

    public static Document toInsertCustomerProducts(CustomerProducts cp){
        Document doc = new Document();
        doc.put("customerId", cp.getCustomerId());
        doc.put("productId", cp.getProductId());
        return doc;
    }

    public static Document toInsertCustomer(Customers customer){
        Document doc = new Document();
        doc.put("id", customer.getId());
        doc.put("customerName", customer.getName());
        doc.put("phoneNumber", customer.getPhoneNumber());
        doc.put("emailAddress", customer.getEmailAddress());
        doc.put("homeAddress", customer.getHomeAddress());
        return doc;
    }

    public static Document toInsertCustOrd(Customers customer){
        Document doc = new Document();
        doc.put("customerId", customer.getId());
        return doc;
    }

    public static JsonObject toJSON(Products p, String customerId){
        return Json.createObjectBuilder()
                    .add("productId", p.getId())
                    .add("customerId", customerId)
                    .build();
    }

}
