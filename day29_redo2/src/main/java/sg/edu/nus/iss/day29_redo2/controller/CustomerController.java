package sg.edu.nus.iss.day29_redo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.day29_redo2.models.Customers;
import sg.edu.nus.iss.day29_redo2.service.EcommerceService;

@Controller
@RequestMapping(path = "/customers/information")
public class CustomerController {

    @Autowired
    private EcommerceService service;

    @GetMapping(path = "/")
    public String getCustomerInfo(Model model){
        model.addAttribute("customers", new Customers());
        return "customerInfo";
    }

    @PostMapping(path = "/postCustomerInfo")
    public String postCustomerInfo(@Valid Customers customers, BindingResult binding,
        Model model, HttpSession session){
        
        if(binding.hasErrors()){
            return "customerInfo";
        }
        String customerId = service.insertCustInfo(customers);
        session.setAttribute("customerId", customerId);
        model.addAttribute("customerId", customerId);
        return "postCustomerInfo";
    }

}
