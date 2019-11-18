package com.otienochris.springmvcguru.services.jpaservices;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public abstract class AbstractJpaDaoService {

    EntityManagerFactory emf;

    @PersistenceUnit
    void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

}
