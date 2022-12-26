package de.predic8.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.FlexibleAggregationStrategy;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.JacksonXMLDataFormat;

import java.util.ArrayList;

public class Wholesale {

    public static void main(String[] args) throws Exception {

        JacksonXMLDataFormat xml = new JacksonXMLDataFormat();
        xml.setUnmarshalType(Item.class);

        JacksonDataFormat json = new JacksonDataFormat();
        json.setPrettyPrint(true);
//        json.setUnmarshalType(Item.class);

        // @formatter:off

        RouteBuilder builder = new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:in?noop=true")
//                        .log("File passed ${header.CamelFileName}.")
//                        .filter(header(Exchange.FILE_NAME).endsWith("xml"))
                        .filter(header(Exchange.FILE_NAME).regex(".*\\.csv|.*\\.xml"))
                        .choice()
                            .when(header(Exchange.FILE_NAME).endsWith("xml"))
                            .split(xpath("//item"))
                                .unmarshal(xml)
                                .enrich().simple("http://localhost:8888/supplier/?food=${body.good}")
                                    .aggregationStrategy(new AddCountry())
                                .log("FileName: ${header.CamelFileName}")
                                .log("class: ${body.class}")
                                .log("good: ${body.good}")
                                .aggregate(simple("${body.country}"), new FlexibleAggregationStrategy<>()
                                    .storeInBody()
                                    .pick(simple("${body}"))
                                    .accumulateInCollection(ArrayList.class))
                                    .completionSize(3).completionInterval(1000)
                                .marshal(json)
                                .to("file:out/json?fileName=item-${exchangeProperty.CamelSplitIndex}.json")
                                .end()
                            .endChoice()
                        .otherwise()
                            .to("file:out/csv");
            }
        };

        // @formatter:on

        CamelContext cc = new DefaultCamelContext();
        cc.addRoutes(builder);

        System.out.println("Started.");
        cc.setDebugging(true);
        cc.start();

        System.out.println("Hit enter to stop");
        System.in.read();
        cc.stop();
    }
}
