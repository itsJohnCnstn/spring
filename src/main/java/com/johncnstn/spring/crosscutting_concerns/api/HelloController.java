package com.johncnstn.spring.crosscutting_concerns.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    /*
        http localhost:8080/hello

        or

        curl -X GET --location "http://localhost:8080/hello" \
        -H "Accept: application/json"
     */
    @GetMapping("/hello")
    public String sayHello() {
        return "Hey there!";
    }

}
