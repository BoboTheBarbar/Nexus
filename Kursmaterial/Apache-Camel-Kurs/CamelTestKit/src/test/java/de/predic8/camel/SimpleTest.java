package de.predic8.camel;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import de.predic8.camel.SimpleRoute;

public class SimpleTest extends CamelTestSupport {
	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new SimpleRoute();
	}
	
	@Test
	public void testRoute() throws Exception {
		File target = new File("target/outbox/hello.txt");
		if (target.exists())
			if (!target.delete())
				throw new RuntimeException("Could not delete file: " + target.getAbsolutePath());
		
		template.sendBodyAndHeader("file://target/inbox", "Hello World", Exchange.FILE_NAME, "hello.txt");
		
		Thread.sleep(1000);
		
		assertTrue("File not moved", target.exists());
	}
}
