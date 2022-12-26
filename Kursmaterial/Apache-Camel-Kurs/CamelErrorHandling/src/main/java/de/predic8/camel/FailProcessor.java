package de.predic8.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class FailProcessor implements Processor {

	private int i = 0;
	
	@Override
	public void process(Exchange exc) throws Exception {
		if ( i++ < 5 ) {
			throw new Exception("Something went wrong!");
		}
	}

}
