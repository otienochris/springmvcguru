package com.otienochris.springmvcguru.services;

import com.otienochris.springmvcguru.config.JpaIntegrationConfig;
import com.otienochris.springmvcguru.models.Customer;
import com.otienochris.springmvcguru.models.User;
import com.otienochris.springmvcguru.services.servicesinterfaces.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@Import(JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class UserServiceJpaDaoImplTest {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void testSaveOrUpdate(){
        User user = new User();
        user.setUsername("someusername");
        user.setPassword("myPassword");

        User savedUser = userService.saveOrUpdate(user);

        assert savedUser.getId() != null;
        assert savedUser.getEncryptedPassword() != null;

        System.out.println("Encrypted Password");
        System.err.println(savedUser.getEncryptedPassword()); // print in the std err stream in order to see it

    }

    @Test
    public void testSaveOrUpdateWithCustomer() throws Exception{
        User user = new User();
        user.setUsername("someusername");
        user.setPassword("myPassword");

        Customer customer = new Customer();
        customer.setFirstName("Cherry");
        customer.setLastName("Chase");

        user.setCustomer(customer);

        User savedUser = userService.saveOrUpdate(user);

        assert savedUser.getId() != null;
        assert savedUser.getId() != null;
        assert savedUser.getCustomer() != null;
        assert savedUser.getCustomer().getId() != null;
    }
}
