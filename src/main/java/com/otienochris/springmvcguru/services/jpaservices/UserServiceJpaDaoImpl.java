package com.otienochris.springmvcguru.services.jpaservices;

import com.otienochris.springmvcguru.models.DomainObject;
import com.otienochris.springmvcguru.models.User;
import com.otienochris.springmvcguru.services.servicesinterfaces.UserService;

import java.util.List;

public class UserServiceJpaDaoImpl extends AbstractJpaDaoService implements UserService {

    @Override
    public List<?> listAll() {
        return null;
    }

    @Override
    public User getById(Integer id) {
        return null;
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
