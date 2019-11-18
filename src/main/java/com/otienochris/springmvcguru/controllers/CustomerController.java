package com.otienochris.springmvcguru.controllers;

import com.otienochris.springmvcguru.models.Customer;
import com.otienochris.springmvcguru.services.servicesinterfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer/")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping({"list", ""})
    public String listCustomers(Model model){
        model.addAttribute("customers", customerService.listAll());
        return "customer/list";
    }

    @GetMapping("show/{id}")
    public String showCustomer(@PathVariable Integer id, Model model){
        model.addAttribute("customer", customerService.getById(id));
        return "customer/show";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("customer", customerService.getById(id));
        return "customer/customerForm";
    }

    @GetMapping("new")
    public String newCustomer(Model model){
        model.addAttribute("customer", new Customer());
        return "customer/customerForm";
    }

    @PostMapping
    public String saveOrUpdate(Customer customer){
        Customer newCustomer = customerService.saveOrUpdate(customer);
        return "redirect:/customer/show/" + newCustomer.getId();
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id){
        customerService.delete(id);
        return "redirect:/customer/list";
    }

}
