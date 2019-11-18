package com.otienochris.springmvcguru.services.mapservices;

import com.otienochris.springmvcguru.models.DomainObject;
import com.otienochris.springmvcguru.models.User;
import com.otienochris.springmvcguru.services.AbstractMapService;
import com.otienochris.springmvcguru.services.servicesinterfaces.UserService;

import java.util.List;

public class UserServiceImpl extends AbstractMapService implements UserService {

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public User getById(Integer id) {
        return (User) super.getById(id);
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        return (User) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

}
