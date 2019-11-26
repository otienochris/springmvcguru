package com.otienochris.springmvcguru.controllers;

import com.otienochris.springmvcguru.models.Address;
import com.otienochris.springmvcguru.models.Customer;
import com.otienochris.springmvcguru.services.servicesinterfaces.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // initializes mock and controller
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void listCustomers() throws Exception {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());

        when(customerService.listAll()).thenReturn((List)customers);

        mockMvc.perform(get("/customer/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/list"))
                .andExpect(model().attribute("customers", hasSize(2)));
    }

    @Test
    void showCustomer() throws Exception {
        Integer id = 1;
        when(customerService.getById(id)).thenReturn(new Customer());

        mockMvc.perform(get("/customer/show/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/show"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    void edit() throws Exception {
        Integer id = 1;
        when(customerService.getById(id)).thenReturn(new Customer());

        mockMvc.perform(get("/customer/edit/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerForm"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    void newCustomer() throws Exception {
        verifyNoInteractions(customerService);

        mockMvc.perform(get("/customer/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerForm"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    void saveOrUpdate() throws Exception {
        Integer id = 1;
        String fname = "Michael";
        String lname = "Weston";
        String add1 = "1 Main St";
        String add2 = " ";
        String city = "Miami";
        String state = "Florida";
        String zip = "33101";
        String email = "michael@wetson.com";
        String tel = "305.333.0101";

        Customer returnCustomer = new Customer();
//        returnCustomer.setId(id);
        returnCustomer.setFirstName(fname);
        returnCustomer.setLastName(lname);
        returnCustomer.setShippingAddress(new Address());
        returnCustomer.getShippingAddress().setAddressLine1(add1);
        returnCustomer.getShippingAddress().setCity(city);
        returnCustomer.getShippingAddress().setState(state);
        returnCustomer.getShippingAddress().setZipCode(zip);
        returnCustomer.setEmail(email);
        returnCustomer.setPhoneNumber(tel);

        when(customerService.saveOrUpdate(Mockito.any(Customer.class))).thenReturn(returnCustomer);

        mockMvc.perform(post("/customer/")
//                .param("id", "1")
                .param("firstName", fname)
                .param("lastName", lname)
                .param("shippingAddress.addressLine1", add1)
                .param("shippingAddress.city", city)
                .param("shippingAddress.state", state)
                .param("shippingAddress.zipCode", zip)
                .param("email", email)
                .param("phoneNumber", tel))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/customer/show/1"))
                    .andExpect(model().attribute("customer", instanceOf(Customer.class)))
                    .andExpect(model().attribute("customer", hasProperty("id", is(id))))
                    .andExpect(model().attribute("customer", hasProperty("firstName", is(fname))))
                    .andExpect(model().attribute("customer", hasProperty("lastName", is(lname))))
                    .andExpect(model().attribute("customer", hasProperty( "shippingAddress ",hasProperty("addressLine1", is(add1)))))
                    .andExpect(model().attribute("customer", hasProperty( "shippingAddress ",hasProperty("city", is(city)))))
                    .andExpect(model().attribute("customer", hasProperty( "shippingAddress ",hasProperty("state", is(state)))))
                    .andExpect(model().attribute("customer", hasProperty( "shippingAddress ",hasProperty("zipCode", is(zip)))))
                    .andExpect(model().attribute("customer", hasProperty("email", is(email))))
                    .andExpect(model().attribute("customer", hasProperty("phoneNumber", is(tel))));

        // test the bound object
        ArgumentCaptor<Customer> boundCustomer = ArgumentCaptor.forClass(Customer.class);
        verify(customerService).saveOrUpdate(boundCustomer.capture());

        assertEquals(id, boundCustomer.getValue().getId());
        assertEquals(fname, boundCustomer.getValue().getFirstName());
        assertEquals(lname, boundCustomer.getValue().getLastName());
        assertEquals(add1, boundCustomer.getValue().getShippingAddress().getAddressLine1());
        assertEquals(city, boundCustomer.getValue().getShippingAddress().getCity());
        assertEquals(state, boundCustomer.getValue().getShippingAddress().getState());
        assertEquals(zip, boundCustomer.getValue().getShippingAddress().getZipCode());
        assertEquals(email, boundCustomer.getValue().getEmail());
        assertEquals(tel, boundCustomer.getValue().getPhoneNumber());
    }

    @Test
    void delete() throws Exception {
        Integer id = 1;

        mockMvc.perform(get("/customer/delete/" + id))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/list"));

        verify(customerService, times(1)).delete(id);
    }
}