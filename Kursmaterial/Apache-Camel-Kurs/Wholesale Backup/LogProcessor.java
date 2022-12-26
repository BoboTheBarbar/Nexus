package de.predic8.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class LogProcessor implements Processor {

	@Override
	public void process(Exchange exc) throws Exception {
		System.out.println("Received: "+exc.getIn().getHeader("CamelFileName"));
	}

}
