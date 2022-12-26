package de.predic8.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelTimer {

	public static void main(String[] args) throws Exception {
		
		RouteBuilder builder = new RouteBuilder() {
		    public void configure() {
		    }
		};
		
		CamelContext ctx = new DefaultCamelContext();
		ctx.addRoutes(builder);

		ctx.start();
		System.out.println("Camel started.");

		System.out.println("");
		System.out.println("------>    Hit Enter to stop.    <------");
		System.out.println("");
		System.in.read();
		System.out.println("");

		ctx.stop();
		System.out.println("Camel stopped.");
		
	}

}
