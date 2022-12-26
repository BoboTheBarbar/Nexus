package de.predic8.camel.advanced;

import org.apache.camel.CamelContext;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.concurrent.Callable;

@RestController
public class DemoController {

    @Autowired
    CamelContext camelContext;

    ProducerTemplate producerTemplate;

    @PostConstruct
    public void init() {
        producerTemplate = camelContext.createProducerTemplate();
    }

    @GetMapping(value = "/users/", produces = "application/json")
    public byte[] getUsers() {
        return handleError(() -> producerTemplate.requestBody("direct:getUsers", null, byte[].class));
    }

    public <T> T handleError(Callable<T> callable) {
        try {
            return callable.call();
        } catch (CamelExecutionException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
