package com.johncnstn.spring.crosscutting_concerns.service;

import org.springframework.stereotype.Service;

@Service
public class TargetServiceJustClass {

    public void doBusinessLogic() {
        System.out.println("I'm an actual call!! --> " + this.getClass() + " doing some businessLogic..");
    }

}
