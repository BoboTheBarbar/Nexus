package de.predic8.auftrag;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;

@Component
public class ConsumerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:autrag-eingang")
                .routeId("abc")
                .log("${body}")
                .to("activemq:auftrag-eingang-queue");

        JacksonDataFormat json = new JacksonDataFormat();
        json.setPrettyPrint(true);

        from("activemq:auftrag-eingang-queue")
                .routeId("verarbeitung")
                .process(new AuftragsProcessor())
                .marshal(json)
                .log("${body}")
                .to("activemq:bestaetigt");
    }
}
