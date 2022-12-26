package de.predic8.auftrag;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockComponent;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuftragApplicationTests {

    @ParameterizedTest
    @MethodSource("values")
    void contextLoads(Integer a, Integer b, int result) throws Exception {

        AdditionsProzessor additionsProzessor = new AdditionsProzessor();

        CamelContext cc = new DefaultCamelContext();
        Exchange exchange = new DefaultExchange(cc);

        List<Integer> ints = new ArrayList<>();
        ints.add(a);
        ints.add(b);
        exchange.getIn().setBody(ints);

        additionsProzessor.process(exchange);

        assertEquals(result, exchange.getIn().getBody(Integer.class));
    }

    private static Stream<Arguments> values() {
        return Stream.of(
                Arguments.of(1,2,3),
                Arguments.of(3,2,5)
        );
    }

    @Test
    public void firstTest() throws Exception {
        CamelContext cc = new DefaultCamelContext();
        RouteBuilder routeBuilder = new ConsumerRoute();
        cc.addRoutes(routeBuilder);

        MockEndpoint mockEndpoint = new MockEndpoint("mock:endpoint", new MockComponent(cc));

        AdviceWith.adviceWith(cc, "verarbeitung", in -> {
           in.replaceFromWith("direct:auftrag-eingang-queue");
           in.interceptSendToEndpoint("activemq:bestaetigt")
                   .skipSendToOriginalEndpoint()
                   .to(mockEndpoint);
        });

        cc.start();

        ProducerTemplate producerTemplate = cc.createProducerTemplate();
        Exchange exchange = new DefaultExchange(cc);
        exchange.getMessage().setBody("{'abc':'abc'}");
        producerTemplate.send("direct:auftrag-eingang-queue", exchange);
//        producerTemplate.sendBody("direct:auftrag-eingang-queue", "{'abc':'abc'}");

        cc.stop();
    }

    @Test
    public void secondTest() throws Exception {
        CamelContext cc = new DefaultCamelContext();

        MockEndpoint me = new MockEndpoint("mock:endpoint",new MockComponent(cc));

        ConsumerRoute cr = new ConsumerRoute();
        cc.addRoutes(cr);
        AdviceWith.adviceWith(cc, "verarbeitung", in -> {
            in.replaceFromWith("direct:auftrag-eingang-queue");
            in.interceptSendToEndpoint("activemq:bestaetigt")
                    .skipSendToOriginalEndpoint()
                    .to(me);
        });

        //        cc.addComponent("activemq", cc.getComponent("direct"));

        cc.start();
        ProducerTemplate pt = cc.createProducerTemplate();

        HashMap<String, Object> map = new HashMap();
        map.put("foo", "bar");


        pt.sendBody("direct:auftrag-eingang-queue",map);

        System.out.println("Test");
    }


    @Test
    public void thirdTest() throws Exception {
        CamelContext cc = new DefaultCamelContext();

        MockEndpoint me = new MockEndpoint("mock:endpoint",new MockComponent(cc));

        ConsumerRoute cr = new ConsumerRoute();
        cc.addRoutes(cr);
        cc.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // direct:bestaetigt

                from("direct:bestaetigt")
                        .log("Message: ${body}")
                        .to(me);
            }
        });


        cc.addComponent("activemq", cc.getComponent("direct"));

        cc.start();
        ProducerTemplate pt = cc.createProducerTemplate();

        HashMap<String, Object> map = new HashMap();
        map.put("foo", "bar");


        pt.sendBody("activemq:auftrag-eingang-queue",map);

        System.out.println("Test");
    }
}