package com.johncnstn.spring.crosscutting_concerns.service.impl;

import com.johncnstn.spring.crosscutting_concerns.service.TargetService;
import org.springframework.stereotype.Service;

@Service
public class TargetServiceImpl implements TargetService {

    /*
          By default, in spring-boot 4 it's a cglib proxy:
          runtimeJoinPointClassName: com.johncnstn.spring.crosscutting_concerns.service.impl.TargetServiceImpl$$SpringCGLIB$$0

          To make it JDK Dynamic:
          spring:
              aop:
                proxy-target-class: false
          --> runtimeJoinPointClassName: jdk.proxy3.$Proxy79
     */
    @Override
    public void doBusinessLogic() {
        System.out.println("I'm an actual call!! --> " + this.getClass() + " doing some businessLogic..");
    }

}
