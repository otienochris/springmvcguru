package com.otienochris.springmvcguru.services.mapservices;

import com.otienochris.springmvcguru.models.Customer;
import com.otienochris.springmvcguru.models.DomainObject;
import com.otienochris.springmvcguru.services.servicesinterfaces.CustomerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("map")
public class CustomerServiceImpl extends AbstractMapService implements CustomerService {

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Customer getById(Integer id) {
        return (Customer) super.getById(id);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        return (Customer) super.saveOrUpdate(domainObject);
    }

    /*@Override
    protected void loadDomainObject() {
        domainMap = new HashMap<>();

        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setFirstName("Michael");
        customer1.setLastName("Weston");
        customer1.setAddressLine1("1 Main St");
        customer1.setCity("Miami");
        customer1.setState("Florida");
        customer1.setZipCode("33101");
        customer1.setEmail("michael@wetson.com");
        customer1.setPhoneNumber("305.333.0101");

        domainMap.put(customer1.getId(), customer1);
    }*/
}
