package sg.edu.nus.iss.day29_redo2.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.day29_redo2.models.CustomerProducts;
import sg.edu.nus.iss.day29_redo2.models.Products;
import sg.edu.nus.iss.day29_redo2.service.EcommerceService;

@Controller
@RequestMapping(path = "/post")
public class AddtoCartController {

    @Autowired
    private EcommerceService service;

    @PostMapping(path = "/addtoCart")
    public String addtoCart(@RequestBody MultiValueMap<String, String> form, 
        HttpSession session,Model model){
        
        System.out.println(">>>>>>>>>" + form);

        String id = form.getFirst("productId");
        String name = form.getFirst("productName");
        Float unitPrice = Float.parseFloat(form.getFirst("unitPrice"));
        Float quantity = Float.parseFloat(form.getFirst("quantity"));

        List<Products> products = (List<Products>) session.getAttribute("addProducts");
        if(null == products){
            products = new LinkedList<>();
            session.setAttribute("addProducts", products);
        } 
        products.add(new Products(id, name, unitPrice, quantity));
        System.out.println(">>>>>>>>>" + products);
        model.addAttribute("productList", products);
        return "addtoCart";
    }

    @PostMapping(path = "/delete")
    public String deleteProduct(@RequestParam("productId")String id, 
        Model model, HttpSession session){

        System.out.println(">>>>>>> id" + id);
        // To remove product from cart
        List<Products> productDel = (List<Products>) session.getAttribute("addProducts");
        if(productDel != null){
            for(Products p : productDel){
                if(p.getProductId() == id ){
                    productDel.remove(p);
                    break;
                }
            }
        }
        System.out.println(">>>>>>> productDel" + productDel);
        model.addAttribute("totalProducts", productDel);
        return "cartTotal";
    }

    // Product tied to the customer id
    @PostMapping(path = "/postList")
    public String postOrder(HttpSession session){
        List<Products> productList = (List<Products>) session.getAttribute("addProducts");
        CustomerProducts cp = new CustomerProducts();
        String customerId = (String) session.getAttribute("customerId");
        cp.setCustomerId(customerId);
        for(Products p : productList){
            String productId = p.getId();
            cp.setCustomerProductId(productId);
        }
        service.insertCustProd(cp);
        return "postList";
    }
    
}
