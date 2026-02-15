package com.johncnstn.spring.crosscutting_concerns.api;

import com.johncnstn.spring.crosscutting_concerns.service.TargetService;
import com.johncnstn.spring.crosscutting_concerns.service.TargetServiceJustClass;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TargetController {

    private final TargetServiceJustClass targetServiceJustClass;
    private final TargetService targetService;

    public TargetController(TargetServiceJustClass targetServiceJustClass,
                            TargetService targetService) {
        this.targetServiceJustClass = targetServiceJustClass;
        this.targetService = targetService;
    }

//    public TargetController(TargetService targetService) {
//        this.targetService = targetService;
//    }

    /*
            http localhost:8080/hello

            or

            curl -X GET --location "http://localhost:8080/hello" \
            -H "Accept: application/json"
         */
    @GetMapping("/hello")
    public String sayHello() {
        targetServiceJustClass.doBusinessLogic();
        System.out.println("Injected targetService class = " + targetService.getClass());
        System.out.println("Injected targetService interfaces = " + java.util.Arrays.toString(targetService.getClass().getInterfaces()));
        targetService.doBusinessLogic();
        return "Hey there!";
    }

}
