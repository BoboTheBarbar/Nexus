package de.predic8.camel;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

import java.util.ArrayList;
import java.util.List;

public class ListStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange aggregate, Exchange newExc) {

        List<Item> items = getItems(aggregate);

        items.add(getItem(newExc));

        newExc.getIn().setBody(items);
        return newExc;
    }

    List<Item> getItems(Exchange exc) {

        return null;

    }

    Item getItem(Exchange exc) {
        return exc.getIn().getBody(Item.class);
    }
}
