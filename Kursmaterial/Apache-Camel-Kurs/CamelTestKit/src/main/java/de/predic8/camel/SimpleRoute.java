package de.predic8.camel;

import org.apache.camel.builder.RouteBuilder;

public class SimpleRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("file://target/inbox").to("file://target/outbox");
	}

}
