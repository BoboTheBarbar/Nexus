package de.predic8.camel.advanced;


import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DemoRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration().port(5555);

        rest("/users")
                .get("/").to("direct:getUsers")
                .post("/").to("direct:addUser");

        from("direct:getUsers")
                .setBody(constant("demo"));

        from("direct:addUser")
                .to("file:out");

    }
}
