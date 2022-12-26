package de.predic8.auftrag;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.List;

public class AdditionsProzessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        List body = exchange.getMessage().getBody(List.class);

        Integer a = (Integer) body.get(0);
        Integer b = (Integer) body.get(1);

        exchange.getIn().setBody(a + b);
    }
}
