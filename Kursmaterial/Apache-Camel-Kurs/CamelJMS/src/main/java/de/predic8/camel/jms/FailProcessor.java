package de.predic8.camel.jms;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class FailProcessor implements Processor {

	private int i = 0;
	
	public void process(Exchange exc) throws Exception {
		if ( i++ < 8 ) {
			throw new Exception("Something went wrong!");
		}
	}

}
