package de.predic8.camel.webservice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class HelloServiceProcessor implements Processor {

	public void process(Exchange exc) throws Exception {
		exc.getOut().setBody(new Object[] { "Hello" });
	}

}
