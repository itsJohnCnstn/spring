package programmatic.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/*
    In this approach, a Spring bean can define custom initialization and destruction logic
    by implementing two Spring-provided interfaces: InitializingBean and DisposableBean.

    afterPropertiesSet(): called after the bean is created and dependencies are injected.
    destroy(): called just before the Spring container is closed.
 */
public class HelloWorld implements InitializingBean, DisposableBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Programmatic destroy() method");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Programmatic init() method");
    }
}
