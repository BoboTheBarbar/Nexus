package de.predic8.auftrag;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.HashMap;

public class AuftragsProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        exchange.getMessage().getBody(HashMap.class).put("processed", true);
    }
}
