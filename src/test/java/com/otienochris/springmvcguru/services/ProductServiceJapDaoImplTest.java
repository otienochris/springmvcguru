package com.otienochris.springmvcguru.services;


import com.otienochris.springmvcguru.config.JpaIntegrationConfig;
import com.otienochris.springmvcguru.models.Product;
import com.otienochris.springmvcguru.services.servicesinterfaces.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@Import(JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class ProductServiceJapDaoImplTest {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Test
    public void testListAll(){
        assert productService.listAll().size() == 5;
    }

    @Test
    public void testGetById(){
        Product product = productService.getById(1);

        assert product.getDescription().equals("Product 1");
        assert product.getImageUrl().equals("http://example.com/image1.png");
        assert product.getPrice().equals(new BigDecimal("13.99"));
    }


}
