package de.predic8.camel;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

public class AddCountry implements AggregationStrategy {

    public Exchange aggregate(Exchange itemExc, Exchange countriesExc) {

        //

        return itemExc;
    }

    private String getCode(Exchange exc) {

        return null;

    }
}
