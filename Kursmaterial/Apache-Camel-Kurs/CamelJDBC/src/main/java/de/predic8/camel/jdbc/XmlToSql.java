package de.predic8.camel.jdbc;

import org.apache.camel.Header;

public class XmlToSql {
	
	public String addPerson(@Header("name") String name, @Header("password") String password) {

		System.out.println("Request");
		System.out.println(" Name: "+name);
		System.out.println(" Password: "+password);
		
		String sql = String.format("", name, password);
		
		System.out.println(" SQL: "+sql);
		return sql;
	}
	
}
