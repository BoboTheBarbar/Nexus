package de.predic8.camel.jms;

import org.apache.xbean.spring.context.*;
import org.springframework.context.*;

import java.io.*;

public class Starter {

    public static void main(String[] args) throws IOException {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/camel-context.xml");

        System.in.read();
    }
}