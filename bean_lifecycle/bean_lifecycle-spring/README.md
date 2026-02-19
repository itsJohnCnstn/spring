# Original article
[Bean Life Cycle in Java Spring](https://www.geeksforgeeks.org/java/bean-life-cycle-in-java-spring/)

# Phases of Spring Bean Life Cycle
A Spring bean typically passes through the following phases:
- Container Initialization: The Spring IoC container is started.
- Bean Instantiation: The container creates an instance of the bean.
- Dependency Injection: Dependencies are injected using constructor, setter, or field injection.
- Initialization Callback: Custom initialization logic is executed.
- Bean Ready for Use: The bean is fully initialized and available for use.
- Container Shutdown: The Spring container is closed.
- Destruction Callback: Cleanup logic is executed before bean destruction.
![img.png](../src/main/resources/static/img.png)
# Console output:
```text
Bean initialized using XML!
Programmatic destroy() method
Bean initialized using annotations!
Bean destroyed using annotations!
Programmatic init() method
Bean destroyed using XML!
```
