package de.predic8.camel;


public class Shop {

	public String order(String order) throws Exception {
		System.out.println("Received: "+ order);
		return "Accepted";
	}
}
