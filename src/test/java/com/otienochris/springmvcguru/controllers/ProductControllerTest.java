package com.otienochris.springmvcguru.controllers;

import com.otienochris.springmvcguru.models.Product;
import com.otienochris.springmvcguru.services.servicesinterfaces.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest {

    @Mock // mockito mock object
    private ProductService productService;

    @InjectMocks // setups controller, and injects mock objects into it
    private ProductController productController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // initializes mocks and controllers
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void listProducts() throws Exception {
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());

        when(productService.listAll()).thenReturn((List) products); // need to strip generics to

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/list"))
                .andExpect(model().attribute("products", hasSize(2)));
    }

    @Test
    void getProduct() throws Exception {
        Integer id = 1;

        when(productService.getById(id)).thenReturn(new Product());

        mockMvc.perform(get("/product/show/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("product/show"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));
    }

    @Test
    void newProduct() throws Exception {

        //should not call service
        verifyNoInteractions(productService);

        mockMvc.perform(get("/product/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/productForm"))
                .andExpect(model().attribute("newProduct", instanceOf(Product.class)));
    }

    @Test
    void saveOrUpdateProduct() throws Exception {

        Integer id = 1;
        String desc = "Test Description";
        BigDecimal price = new BigDecimal("12.00");
        String imageUrl = "http://example.com/product1";

        // create a product
        Product returnProduct = new Product();
        returnProduct.setId(id);
        returnProduct.setDescription(desc);
        returnProduct.setPrice(price);
        returnProduct.setImageUrl(imageUrl);

        when(productService.saveOrUpdate(Mockito.any(Product.class))).thenReturn(returnProduct);

        mockMvc.perform(post("/product/")
                .param("id", "1")
                .param("description", desc)
                .param("price", "12.00")
                .param("imageUrl", imageUrl))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/product/show/1"))
                    .andExpect(model().attribute("product", instanceOf(Product.class)))
                    .andExpect(model().attribute("product", hasProperty("id", is(id))))
                    .andExpect(model().attribute("product", hasProperty("description", is(desc))))
                    .andExpect(model().attribute("product", hasProperty("price", is(price))))
                    .andExpect(model().attribute("product", hasProperty("imageUrl", is(imageUrl))));

        // verify properties of the bound object
        ArgumentCaptor<Product> boundProduct = ArgumentCaptor.forClass(Product.class);
        verify(productService).saveOrUpdate(boundProduct.capture());

        assertEquals(id, boundProduct.getValue().getId());
        assertEquals(desc, boundProduct.getValue().getDescription());
        assertEquals(price, boundProduct.getValue().getPrice());
        assertEquals(imageUrl, boundProduct.getValue().getImageUrl());
    }

    @Test
    void edit() throws Exception {
        Integer id = 1;

        when(productService.getById(id)).thenReturn(new Product());

        mockMvc.perform(get("/product/edit/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("product/productForm"))
                .andExpect(model().attribute("newProduct", instanceOf(Product.class)));
    }

    @Test
    void delete() throws Exception {

        mockMvc.perform(get("/product/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/product/list"));

        verify(productService, times(1)).delete(1);
    }
}