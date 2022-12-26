package de.predic8.tmp;

import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class Main {

    private static BinaryOperator<Item> itemAggregator = (aggregatedResult, nextElement) -> {
        aggregatedResult.data += nextElement.data;
        aggregatedResult.dataValue += nextElement.dataValue;
        return aggregatedResult;
    };

    public static void something(List<Item> initialItemList) {
        Set<String> uniqueItemIds = initialItemList.stream()
                .map(item -> item.itemId)
                .collect(Collectors.toSet());

        List<Item> resultList = uniqueItemIds.stream()
                .map(itemId -> new Item(0, 0, itemId))
                .collect(Collectors.toList());

        resultList.forEach(
                resultItem -> {
                    initialItemList.stream()
                            .filter(item -> item.itemId.equals(resultItem.itemId))
                            .reduce(resultItem, itemAggregator);
        });
    }

    static class Item {
        public int data;
        public int dataValue;
        public String itemId;

        public Item(int data, int dataValue, String itemId) {
            this.data = data;
            this.dataValue = dataValue;
            this.itemId = itemId;
        }
    }
}

