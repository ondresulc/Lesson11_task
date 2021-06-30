package com.company;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        DatabaseFunctions db = new DatabaseFunctions();

        /**
         * This method loads an item with given id
         **/
        System.out.println(db.loadItemById(20));

        /**
         * This method deletes all items that are not in stock
         */
        db.deleteAllOutOfStockItems();


        /**
         * This method loads all items that are in stock
         */
        db.loadAllAvailableItems();


        /**
          * This method saves the given item
         */
//        db.saveItem(new Item ("4444",
//                "923456",
//                "tablecloth",
//                "kitchen tablecloth",
//                1,
//                BigDecimal.valueOf(129)));

        /**
         * This method updates a price of an item
         */
        db.updatePrice(15, BigDecimal.valueOf(89));
    }
}
