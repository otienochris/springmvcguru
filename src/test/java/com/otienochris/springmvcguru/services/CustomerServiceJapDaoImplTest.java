package com.otienochris.springmvcguru.services;

import com.otienochris.springmvcguru.config.JpaIntegrationConfig;
import com.otienochris.springmvcguru.models.Customer;
import com.otienochris.springmvcguru.services.servicesinterfaces.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("jpadao")
@Import(JpaIntegrationConfig.class)
public class CustomerServiceJapDaoImplTest {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Test
    public void testListAll() {
        List<Customer> customers = (List<Customer>) customerService.listAll();

        assert customers.size() == 3;
    }

    @Test
    public void testGetById(){
        Customer product = customerService.getById(6);
        assert product != null;
        assert product.getCity() == "Nairobi";
        assert product.getState() == "Kenya";

//        TODO -- other properties shoud be tested also to ensure extensive test
    }

    @Test
    public void testSaveOrUpdate(){
        Customer customer = new Customer();
        customer.setFirstName("Michael");
        customer.setLastName("Weston");
        customer.setAddressLine1("1 Main St");
        customer.setCity("Miami");
        customer.setState("Florida");
        customer.setZipCode("33101");
        customer.setEmail("michael@wetson.com");
        customer.setPhoneNumber("305.333.0101");

        customerService.saveOrUpdate(customer);

        assert customerService.listAll().size() == 4; // ensure it is persisted to the db
    }

    @Test
    public void testDelete(){
        customerService.delete(6);
        assert customerService.listAll().size() == 2;
    }
}
