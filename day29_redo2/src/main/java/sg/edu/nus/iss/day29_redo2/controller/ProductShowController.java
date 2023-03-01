package sg.edu.nus.iss.day29_redo2.controller;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.day29_redo2.models.Products;
import sg.edu.nus.iss.day29_redo2.models.Retailers;
import sg.edu.nus.iss.day29_redo2.service.EcommerceService;

@Controller
public class ProductShowController {

    @Autowired
    private EcommerceService ecommerceService;

    @GetMapping(path = {"/", "/index.html"})
    public String mainProduct(Model model){
        model.addAttribute("retailer", new Retailers());
        model.addAttribute("product", new Products());
        return "index";
    }

    @PostMapping(path = "/postProduct")
    public String validation(@Valid @ModelAttribute("retailers") Retailers retailers, 
        @Valid @ModelAttribute("products") Products products, BindingResult binding, 
        Model model, HttpSession session){
        
        if(binding.hasErrors()){
            List<ObjectError> errors = binding.getAllErrors();
            for(ObjectError error : errors) {
                System.out.println(">>>>>>>>" + error);
            }
            return "index";
        }

        List<Products> productList = (List<Products>) session.getAttribute("products");
        if(null == productList){
            productList = new LinkedList<>();
            session.setAttribute("products", productList);
        }
        productList.add(products);
        System.out.println(">>>>>>>>" + productList);
        System.out.println(productList.get(0));

        model.addAttribute("productList", productList);
        session.setAttribute("retailers", retailers);
        System.out.println(">>>>>>" + retailers);
        return "product";
    }

    @PostMapping(path = "/insertProducts")
    public String postProduct(HttpSession session){

        List<Products> productList = (List<Products>) session.getAttribute("products");
        System.out.println(productList);
        
        Retailers retailers = (Retailers) session.getAttribute("retailers");
        System.out.println(retailers);

        String id = ecommerceService.insertRetailer(retailers);
        ecommerceService.insertProduct(productList, id);

        session.invalidate();
        return "productPostRetailer";
    }

    @GetMapping(path = "/allProducts")
    public String getAllProducts(Model model){
        List<Document> allProducts = ecommerceService.getAllProducts();
        model.addAttribute("products", allProducts);
        return "allProducts";
    }
    
}
