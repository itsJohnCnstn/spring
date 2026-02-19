package com.johncnstn.springboot;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Map;

/*
    Constructor
        → Dependency Injection
        → @PostConstruct
        → afterPropertiesSet()
        → custom init-method
 */
@SpringBootApplication
public class Application {

    static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Service
    private class ExampleService implements InitializingBean {

        private final ExampleRepository exampleRepository;

        // mustn't be final!
        private ExampleComponent exampleComponent;
        private Map<String, Object> cache;

        private ExampleService(ExampleRepository exampleRepository) {
            log("Constructor", "initializing Service");
            this.exampleRepository = exampleRepository;
        }

        @Autowired
        void methodInjection(ExampleComponent exampleComponent) {
            log("@Autowired", "not necessary exampleComponent");
        }

        @PostConstruct
        private void warmUpCache() {
            log("@PostConstruct", "warming up cache");
        }

        @Override
        public void afterPropertiesSet() {
            // validate internal state
            // prepare proxies
            log("afterPropertiesSet", "");
        }
    }

    @Repository
    private class ExampleRepository {

    }

    @Component
    public class ExampleComponent {

    }

    /*
        real output:
            Constructor - initializing Service!
            @Autowired - not necessary exampleComponent!
            @PostConstruct - warming up cache!
            afterPropertiesSet - !
     */
    private static void log(String step, String message) {
        System.out.printf("%s - %s!%n", step, message);
    }

}
