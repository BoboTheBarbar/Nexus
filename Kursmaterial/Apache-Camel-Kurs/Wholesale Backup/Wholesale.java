package de.predic8.camel;

import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.JacksonXMLDataFormat;

import static org.apache.camel.Exchange.FILE_NAME;

public class Wholesale {

       public static void main(String[] args) throws Exception {

           RouteBuilder builder = new RouteBuilder() {
               public void configure(){

               }
           };



           CamelContext ctx = new DefaultCamelContext();

        ctx.addRoutes(builder);

        System.out.println("Started.");
        ctx.start();

        System.out.println("------>    Hit Enter to stop.    <------");
        System.in.read();

        ctx.stop();

    };

}
