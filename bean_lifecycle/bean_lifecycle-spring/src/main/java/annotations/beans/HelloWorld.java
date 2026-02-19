package annotations.beans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class HelloWorld {

    @PostConstruct
    public void init() {
        System.out.println("Bean initialized using annotations!");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Bean destroyed using annotations!");
    }

}
