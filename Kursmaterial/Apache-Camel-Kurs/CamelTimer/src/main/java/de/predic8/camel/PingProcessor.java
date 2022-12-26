package de.predic8.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class PingProcessor implements Processor {

	public void process(Exchange exc){
		System.out.println(exc.getProperty(Exchange.TIMER_FIRED_TIME)+": Ping!");
	}

}
