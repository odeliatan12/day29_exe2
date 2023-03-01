package sg.edu.nus.iss.day29_redo2.service;

import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.day29_redo2.models.CustomerProducts;
import sg.edu.nus.iss.day29_redo2.models.Customers;
import sg.edu.nus.iss.day29_redo2.models.Products;
import sg.edu.nus.iss.day29_redo2.models.Retailers;
import sg.edu.nus.iss.day29_redo2.repo.EcommerceRepoImpl;

@Service
public class EcommerceService {

    @Autowired
    private EcommerceRepoImpl repo;

    public String insertRetailer(Retailers retailers){
        String retailerId = UUID.randomUUID().toString()
            .substring(0, 8);
        repo.toInsertRetailer(retailers, retailerId);
        return retailerId;
    }

    public void insertProduct(List<Products> list, String id){
        repo.toInsertProduct(list, id);
    }

    public List<Document> getAllProducts(){
        return repo.getAllProducts();
    }

    public Document removeProduct(String id){
        return repo.deleteProduct(id);
    }

    public String insertCustInfo(Customers c){
        String custId = UUID.randomUUID().toString()
            .substring(0, 8);
        c.setId(custId);
        repo.toInsertCustInfo(c);
        return custId;
    }

    public void insertCustProd(CustomerProducts cp){
        repo.toInsertCustProd(cp);
    }
    
}
