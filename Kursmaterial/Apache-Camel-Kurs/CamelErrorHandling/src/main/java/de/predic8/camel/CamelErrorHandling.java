package de.predic8.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelErrorHandling {

	public static void main(String[] args) throws Exception {
		
		RouteBuilder builder = new RouteBuilder() {
		    public void configure() {		    	
		        from("file:in?delay=10000&noop=true")
		        .process(new FailProcessor())
                .to("file:out")
						.log("Erfolg !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		    }
		};
		
		CamelContext ctx = new DefaultCamelContext();
		ctx.addRoutes(builder);

		System.out.println("Started.");
		ctx.start();

		System.out.println("-->    Hit Enter to stop.    <--");
		System.in.read();

		ctx.stop();

	}

}
