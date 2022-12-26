package de.predic8.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;

public class CamelProxyExample {

	public static void main(String[] args) throws Exception {
		
		RouteBuilder builder = new RouteBuilder() {
		    public void configure() {
		    	from("direct:order")
		    	.to("bean:shop");
		    }
		};
		
		JndiContext jndi = new JndiContext();
		jndi.bind("shop", new Shop());
		
		CamelContext ctx = new DefaultCamelContext(jndi);
		ctx.addRoutes(builder);
		
		ctx.start();
		System.out.println("Camel started.");
		
		// TODO: insert code here

		System.out.println("");
		System.out.println("------>    Hit Enter to stop.    <------");
		System.out.println("");
		System.in.read();
		System.out.println("");

		ctx.stop();
		System.out.println("Camel stopped.");
		
	}

}
