package com.otienochris.springmvcguru.services.mapservices;

import com.otienochris.springmvcguru.models.DomainObject;
import com.otienochris.springmvcguru.models.Product;
import com.otienochris.springmvcguru.services.AbstractMapService;
import com.otienochris.springmvcguru.services.servicesinterfaces.ProductService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Service
@Profile("map")
public class ProductServiceImp extends AbstractMapService implements ProductService {

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Product getById(Integer id) {
        return (Product) super.getById(id);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        return (Product) super.saveOrUpdate(domainObject);
    }

/*    @Override
    protected void loadDomainObject() {
        domainMap = new HashMap<>();

        Product product1 = new Product();
        product1.setId(1);
        product1.setDescription("Product 1");
        product1.setPrice(new BigDecimal("12.99"));
        product1.setImageUrl("http://example.com/product1");

        domainMap.put(1, product1);
    }*/

}
