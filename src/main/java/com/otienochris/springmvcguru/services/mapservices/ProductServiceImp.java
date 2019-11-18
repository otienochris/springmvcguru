package com.otienochris.springmvcguru.services.mapservices;

import com.otienochris.springmvcguru.models.DomainObject;
import com.otienochris.springmvcguru.models.Product;
import com.otienochris.springmvcguru.services.servicesinterfaces.ProductService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

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

}
