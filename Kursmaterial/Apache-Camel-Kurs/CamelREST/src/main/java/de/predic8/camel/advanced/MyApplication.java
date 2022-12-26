package de.predic8.camel.advanced;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(MyApplication.class, args);

        System.in.read();
    }

}
