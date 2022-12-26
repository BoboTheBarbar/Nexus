package de.predic8.camel.advanced;

import org.apache.camel.Exchange;

public class UserManager {

  public void addUser(Exchange exc) {

    System.out.println("User angelegt");
    System.out.println(" Name: "+exc.getIn().getHeader("name"));
    System.out.println(" Password:"+exc.getIn().getHeader("password"));

  }

}

