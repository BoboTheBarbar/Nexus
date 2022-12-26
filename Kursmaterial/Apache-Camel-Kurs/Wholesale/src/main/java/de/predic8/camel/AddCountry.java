package de.predic8.camel;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

public class AddCountry implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange itemExchange, Exchange countryExchange) {
        Item item = itemExchange.getIn().getBody(Item.class);
        item.setCountry(getCountry(countryExchange));
        return itemExchange;
    }

    private String getCountry(Exchange newExchange) {
        return newExchange.getIn().getBody(String.class);
    }
}
