package de.predic8.camel.jms;

import org.apache.camel.builder.RouteBuilder;

public class ConsumerRoute extends RouteBuilder {

    public void configure() {
    	from("activemq:documentIn")
    	.to("activemq:documentOut");
    }
}
