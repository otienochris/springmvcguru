package com.otienochris.springmvcguru.services;

import com.otienochris.springmvcguru.config.JpaIntegrationConfig;
import com.otienochris.springmvcguru.models.*;
import com.otienochris.springmvcguru.services.servicesinterfaces.ProductService;
import com.otienochris.springmvcguru.services.servicesinterfaces.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@Import(JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class UserServiceJpaDaoImplTest {

    private ProductService productService;
    private UserService userService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

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

        user.setCustomer(customer); // creates the customer in user and user in customer

        User savedUser = userService.saveOrUpdate(user);

        assert savedUser.getId() != null;
        assert savedUser.getId() != null;
        assert savedUser.getCustomer() != null;
        assert savedUser.getCustomer().getId() != null;
    }

    @Test
    public void testAddCartToUser() throws Exception{
        User user = new User();

        user.setUsername("Some User");
        user.setPassword("My Password");

        user.setCart(new Cart());

        User savedUser = userService.saveOrUpdate(user);
        assert savedUser.getId() != null;
        assert savedUser.getVersion() != null;
        assert savedUser.getCart() != null;
        assert savedUser.getCart().getId() != null;
    }

    @Test
    public void testAddCartToUserWithCartDetail() throws Exception{
        User user = new User();

        user.setUsername("otieno");
        user.setPassword("myPassword");
        user.setCart(new Cart());

        List<Product> storedProduct = (List<Product>) productService.listAll();

        CartDetail cartItemOne = new CartDetail();
        cartItemOne.setProduct(storedProduct.get(0));
        user.getCart().addCartDetail(cartItemOne);


        CartDetail cartItemTwo = new CartDetail();
        cartItemTwo.setProduct(storedProduct.get(1));
        user.getCart().addCartDetail(cartItemTwo);

        User savedUser = userService.saveOrUpdate(user);

        assert savedUser.getId() != null;
        assert savedUser.getVersion() !=  null;
        assert savedUser.getCart() !=  null;
        assert savedUser.getCart().getId() != null;
        assert savedUser.getCart().getCartDetails().size() == 2;
    }

    @Test
    public void testAddAndRemoveCartToUserWithCartDetail() throws Exception{
        User user = new User();

        user.setUsername("otieno");
        user.setPassword("myPassword");
        user.setCart(new Cart());

        List<Product> storedProduct = (List<Product>) productService.listAll();

        CartDetail cartItemOne = new CartDetail();
        cartItemOne.setProduct(storedProduct.get(0));
        user.getCart().addCartDetail(cartItemOne);


        CartDetail cartItemTwo = new CartDetail();
        cartItemTwo.setProduct(storedProduct.get(1));
        user.getCart().addCartDetail(cartItemTwo);

        User savedUser = userService.saveOrUpdate(user);

        assert savedUser.getId() != null;
        assert savedUser.getVersion() !=  null;
        assert savedUser.getCart() !=  null;
        assert savedUser.getCart().getId() != null;
        assert savedUser.getCart().getCartDetails().size() == 2;

//        remove part
        user.getCart().getCartDetails().remove(cartItemTwo);
        userService.saveOrUpdate(user);
        assert user.getCart().getCartDetails().size() == 1;
    }
}
