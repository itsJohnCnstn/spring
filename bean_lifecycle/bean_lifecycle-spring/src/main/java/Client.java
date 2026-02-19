import org.springframework.context.support.ClassPathXmlApplicationContext;

void main() {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("xml_spring.xml",
            "programmatic_spring.xml", "annotations_spring.xml");
    // To trigger the destroy() method, we must explicitly close the Spring container using ConfigurableApplicationContext.close().
    context.close();
}
