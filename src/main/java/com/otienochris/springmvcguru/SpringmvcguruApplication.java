package com.otienochris.springmvcguru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringmvcguruApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringmvcguruApplication.class, args);

        /*System.out.println("Beans ****");
        System.out.println(context.getBeanDefinitionCount());
        for (String name :
             context.getBeanDefinitionNames()) {
            System.out.println(name);
        }*/
    }

}
