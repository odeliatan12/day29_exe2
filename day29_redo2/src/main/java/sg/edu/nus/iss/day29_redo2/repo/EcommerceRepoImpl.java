package sg.edu.nus.iss.day29_redo2.repo;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day29_redo2.Utils;
import sg.edu.nus.iss.day29_redo2.models.CustomerProducts;
import sg.edu.nus.iss.day29_redo2.models.Customers;
import sg.edu.nus.iss.day29_redo2.models.Products;
import sg.edu.nus.iss.day29_redo2.models.Retailers;

import static sg.edu.nus.iss.day29_redo2.Constant.*;

import java.util.List;
import java.util.UUID;

@Repository
public class EcommerceRepoImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Document toInsertRetailer(Retailers retailers, 
        String id){
        Document doc = Utils.toInsertRetailer(retailers, id);
        return mongoTemplate.insert(doc, COLLECTION_RETAILERS);        
    }

    public void toInsertProduct(List<Products> product, 
        String id){
        
        String productId = UUID.randomUUID().toString().substring(0, 8);
        
        List<Document> doc = Utils.toInsertProduct(product, id, productId);
        mongoTemplate.insert(doc, COLLECTION_PRODUCTS);
    }

    public List<Document> getAllProducts(){
        return mongoTemplate.findAll(Document.class, COLLECTION_PRODUCTS);
    }

    public Document deleteProduct(String id){
        Query query = Query.query(
            Criteria.where(FIELD_ID).is(id)
        );
        mongoTemplate.remove(query, COLLECTION_PRODUCTS);
        return mongoTemplate.findAndRemove(query, 
            Document.class, COLLECTION_PRODUCTS);
    }

    public Document toInsertCustInfo(Customers c){
        Document doc = Utils.toInsertCustomer(c);
        Document doc2 = Utils.toInsertCustOrd(c);
        mongoTemplate.insert(doc2, COLLECTION_CUSTOMERORDERS);
        return mongoTemplate.insert(doc, COLLECTION_CUSTOMERS);
    }

    public void toInsertCustProd(CustomerProducts cp){
        Document doc = Utils.toInsertCustomerProducts(cp);
        mongoTemplate.insert(doc, COLLECTION_CUSTOMERPRODUCTS);
    }
    
}
