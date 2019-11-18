package com.otienochris.springmvcguru.bootstrap;

import com.otienochris.springmvcguru.models.Customer;
import com.otienochris.springmvcguru.models.Product;
import com.otienochris.springmvcguru.services.servicesinterfaces.CustomerService;
import com.otienochris.springmvcguru.services.servicesinterfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ProductService productService;
    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) { this.customerService = customerService; }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadProducts();
        loadCustomers();
    }

    private void loadCustomers() {
        Customer customer1 = new Customer();
        customer1.setFirstName("Otieno");
        customer1.setLastName("Christopher");
        customer1.setEmail("otienochris98@gmail.com");
        customer1.setPhoneNumber("07040544670");
        customer1.setCity("Nairobi");
        customer1.setState("Kenya");
        customer1.setZipCode("OP-Q 200-34");
        customer1.setAddressLine1("");
        customer1.setAddressLine2("");
        customerService.saveOrUpdate(customer1);


        Customer customer2 = new Customer();
        customer2.setFirstName("Otis");
        customer2.setLastName("Chris");
        customer2.setEmail("otienochris@gmail.com");
        customer2.setPhoneNumber("0722544670");
        customer2.setCity("Nakuru");
        customer2.setState("Kenya");
        customer2.setZipCode("OP-P 200-34");
        customer2.setAddressLine1("san 2");
        customer2.setAddressLine2("");
        customerService.saveOrUpdate(customer2);


        Customer customer3 = new Customer();
        customer3.setFirstName("Ochieng");
        customer3.setLastName("Charles");
        customer3.setEmail("peterchris98@gmail.com");
        customer3.setPhoneNumber("0740598670");
        customer3.setCity("Nairobi");
        customer3.setState("Uganda");
        customer3.setZipCode("OP-Q 200-34");
        customer3.setAddressLine1("");
        customer3.setAddressLine2("");
        customerService.saveOrUpdate(customer3);
    }

    private void loadProducts() {
        Product pro1 = new Product();
        pro1.setDescription("Product 1");
        pro1.setPrice(new BigDecimal("13.99"));
        pro1.setImageUrl("http://example.com/image1.png");
        productService.saveOrUpdate(pro1);


        Product pro2 = new Product();
        pro2.setDescription("Product 2");
        pro2.setPrice(new BigDecimal("12.99"));
        pro2.setImageUrl("http://example.com/image2.png");
        productService.saveOrUpdate(pro2);


        Product pro3 = new Product();
        pro3.setDescription("Product 3");
        pro3.setPrice(new BigDecimal("112.99"));
        pro3.setImageUrl("http://example.com/image3.png");
        productService.saveOrUpdate(pro3);


        Product pro4 = new Product();
        pro4.setDescription("Product 4");
        pro4.setPrice(new BigDecimal("42.99"));
        pro4.setImageUrl("http://example.com/image4.png");
        productService.saveOrUpdate(pro4);


        Product pro5 = new Product();
        pro5.setDescription("Product 5");
        pro5.setPrice(new BigDecimal("15.99"));
        pro5.setImageUrl("http://example.com/image5.png");
        productService.saveOrUpdate(pro5);
    }
}
